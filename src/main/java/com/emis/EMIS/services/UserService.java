package com.emis.EMIS.services;

import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.security.TokenGenerator;
import com.emis.EMIS.utils.RandomGenerator;
import com.emis.EMIS.utils.ResponseManager;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.UserProfileDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        String password = RandomGenerator.generateChars(4);
        userEntity.setPassword(passwordEncoder.encode(password));
        UserEntity user = dataService.saveUser(userEntity);

        return responseManager.successResponse("Successfully registered user");
    }
    }
