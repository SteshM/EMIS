package com.emis.EMIS.services;

import com.emis.EMIS.enums.UserType;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.security.CustomUserDetails;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.OtpDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public ResponseDTO verifyOTP(OtpDTO otpDTO) {
        boolean isOtpVerified = otpService.verifyOtp(otpDTO);
        if (isOtpVerified){
            return utilities.successResponse("Correct Otp",null);
        }
        return utilities.failedResponse(401,"Incorrect otp",null);

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
