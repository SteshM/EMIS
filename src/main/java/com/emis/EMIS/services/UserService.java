package com.emis.EMIS.services;

import com.emis.EMIS.models.ProfileEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.ResponseManager;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.UserResponseDTO;
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


}
