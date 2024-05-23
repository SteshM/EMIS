package com.emis.EMIS.services;

import com.emis.EMIS.enums.Role;
import com.emis.EMIS.models.ProfileEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.security.TokenGenerator;
import com.emis.EMIS.utils.ResponseManager;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.UserResponseDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Data
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    private final DataService dataService;
    private final ResponseManager responseManager;
    private final TokenGenerator tokenGenerator;

    public ResponseDTO registerUser(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        UserEntity createdUser = dataService.saveUser(userEntity);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName(userEntity.getFirstName());
        userResponseDTO.setMiddleName(userEntity.getMiddleName());
        userResponseDTO.setLastName(userEntity.getLastName());
        userResponseDTO.setEmail(userEntity.getEmail());
        userResponseDTO.setPhoneNo(userEntity.getPhoneNo());

        ProfileEntity profileEntity = modelMapper.map(userDTO,ProfileEntity.class);
        profileEntity.setUser(createdUser);
        profileEntity.setUsername(userDTO.getEmail());
        profileEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        dataService.saveProfile(profileEntity);

        log.info("registered a user:{}",userResponseDTO);
        return responseManager.successResponse(userResponseDTO);

        }


    public ResponseDTO login(UserDTO userDTO) {
        UserEntity user = dataService.getUser(userDTO.getEmail());

        log.info(userDTO.getEmail());
        if (user == null){
            return responseManager.failedResponse("Email doesn't exist",null);
        }
        else{
            if (passwordEncoder.matches(userDTO.getPassword(),user.getPassword())){
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for(Role role: user.getRoles()){
                    authorities.add(new SimpleGrantedAuthority(role.toString()));
                }

                        User user1 = new User(user.getEmail(),user.getPassword(),authorities);
                 Map<String,String> res = new HashMap<>();
                 res.put("token",tokenGenerator.token(user1));
                 res.put("email",userDTO.getEmail());
                 return responseManager.successResponse("successfully logged in");
            }
            else {
                return responseManager.failedResponse("Password Mismatch", 404L);
            }
        }
    }

    }

