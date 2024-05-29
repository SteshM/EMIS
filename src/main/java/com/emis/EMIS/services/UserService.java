package com.emis.EMIS.services;

import com.emis.EMIS.enums.UserType;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.security.CustomUserDetails;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.ActivateAccDTO;
import com.emis.EMIS.wrappers.requestDTOs.AddAuthDto;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final DataService dataService;
    private final Utilities utilities;
    private final OTPService otpService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> credential = dataService.findByEmail(username);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with name :" + username));
    }

    public ResponseDTO registerUser(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            Collection<UserType> userTypes=new ArrayList<>();
            userTypes.add(UserType.valueOf(userDTO.getRoles()));
            userEntity.setRoles(userTypes);
            userEntity.setStatus(false);
            UserEntity savedUser = dataService.saveUser(userEntity);
            //Call OTP Service
            otpService.generateOTP(savedUser);
            UserProfileDTO userProfileDTO = modelMapper.map(savedUser, UserProfileDTO.class);
            return utilities.successResponse("Successfully registered user", userProfileDTO);
        } catch (Exception  psqlException){
            log.error("Caught an exception",psqlException);
            UserEntity userEntity = dataService.findByEmail(userDTO.getEmail()).get();
            UserProfileDTO userProfileDTO = modelMapper.map(userEntity,UserProfileDTO.class);
            return utilities.failedResponse(205,"Email already exists",userProfileDTO);
        }


    }

    public ResponseDTO activateAccount(ActivateAccDTO activateAccDTO) {

        UserEntity userEntity = dataService.findByEmail(activateAccDTO.getEmail()).get();
        if(userEntity != null){
            boolean isOtpVerified = otpService.verifyOtp(userEntity.getUserId(), activateAccDTO.getOtp());
            if(isOtpVerified){
                userEntity.setStatus(true);
                userEntity.setPassword(passwordEncoder.encode(activateAccDTO.getPassword()));
                dataService.saveUser(userEntity);
            }
        }
        return utilities.failedResponse(401,"Incorrect otp",null);

    }


    public ResponseDTO addAuthority(AddAuthDto addAuthDto){
        UserEntity userEntity = dataService.findByEmail(addAuthDto.getEmail()).get();
        if(userEntity != null){
            Collection<UserType> userTypes = userEntity.getRoles();
            userTypes.add(UserType.valueOf(addAuthDto.getAuthority()));
            userEntity.setRoles(userTypes);
            dataService.saveUser(userEntity);
            return utilities.successResponse("Successfully added authority",null);
        }
        return utilities.failedResponse(400,"cant add authority",null);

    }
}
