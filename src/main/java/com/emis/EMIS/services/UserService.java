package com.emis.EMIS.services;

import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.security.CustomUserDetails;
import com.emis.EMIS.utils.Exchanger;
import com.emis.EMIS.utils.RandomGenerator;
import com.emis.EMIS.utils.ResponseManager;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@Data
public class UserService implements UserDetailsService {
    @Autowired
    DataService dataService;
    @Autowired
     ResponseManager responseManager;
    @Autowired
     Exchanger exchanger;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Value("${email.url}")
    private String url;


    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> credential = dataService.findByEmail(username);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with name :" + username));
    }

    public ResponseDTO registerUser(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        String password = RandomGenerator.generateChars(4);
        userEntity.setPassword(passwordEncoder().encode(password));
        UserEntity user = dataService.saveUser(userEntity);
        Map<String, Object> mailMap = new HashMap<>();
        mailMap.put("receiverName", ""+user.getFirstName()+" "+user.getLastName());
        mailMap.put("to", user.getEmail());
        mailMap.put("otp", password);
        mailMap.put("subject", "OTP password EMIS");
        mailMap.put("templateName", "otp");
        exchanger.postRequest(url, mailMap);
        return responseManager.successResponse("Successfully registered user");
    }

}
