package com.emis.EMIS.services;

import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.security.CustomUserDetails;
import com.emis.EMIS.utils.Exchanger;
import com.emis.EMIS.utils.JwtUtil;
import com.emis.EMIS.utils.RandomGenerator;
import com.emis.EMIS.utils.ResponseManager;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.UserProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final DataService dataService;
    private final ResponseManager responseManager;
    private final OTPService otpService;

    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> credential = dataService.findByEmail(username);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with name :" + username));
    }

    public ResponseDTO registerUser(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            UserEntity savedUser = dataService.saveUser(userEntity);
            //Call OTP Service
            otpService.generateOTP(savedUser);
            UserProfileDTO userProfileDTO = modelMapper.map(savedUser, UserProfileDTO.class);
            return responseManager.successResponse("Successfully registered user", userProfileDTO);
        } catch (Exception  psqlException){
            log.error("Caught an exception",psqlException);
            UserEntity userEntity = dataService.findByEmail(userDTO.getEmail()).get();
            UserProfileDTO userProfileDTO = modelMapper.map(userEntity,UserProfileDTO.class);
            return responseManager.failedResponse(205,"Email already exists",userProfileDTO);
        }

    }








    //SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(userDTO.getEmail());
//
//        msg.setSubject("Emis - Your One time pin");
//        msg.setText("This is your temporary pin,  \n " +password);
//
//        javaMailSender.send(msg);

//        Map<String, Object> mailMap = new HashMap<>();
//        mailMap.put("receiverName", ""+user.getFirstName()+" "+user.getLastName());
//        mailMap.put("to", user.getEmail());
//        mailMap.put("otp", password);
//        mailMap.put("subject", "OTP password EMIS");
//        mailMap.put("templateName", "otp");
//        exchanger.postRequest(url, mailMap);

}
