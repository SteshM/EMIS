package com.emis.EMIS.services;

import com.emis.EMIS.models.AgentInfoEntity;
import com.emis.EMIS.models.ProfileEntity;
import com.emis.EMIS.models.RolesEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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


    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> credential = dataService.findByEmail(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Collection<RolesEntity> roles = credential.get().getRoles();

        for (RolesEntity rolesEntity : roles) {
            authorities.add(new SimpleGrantedAuthority(rolesEntity.getRole()));
        }

        return new User(credential.get().getEmail(), credential.get().getPassword(), authorities);
    }

    ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO registerAgent(AgentRequestDTO agentRequestDTO) {

        try {

            UserEntity userEntity = modelMapper.map(agentRequestDTO, UserEntity.class);
            userEntity.setStatus(false);
            int profileId = dataService.findByProfile("agent").getProfileId();
            if (profileId == 0) {
                return utilities.failedResponse(400, "Profile does not exist", null);
            }
            Collection<RolesEntity> roles = new ArrayList<>();
            //adding basic role-->standard
            RolesEntity role = dataService.findRoleById(1);
            roles.add(role);
            //if super admin -> super role
//            if (userDTO.getWho().equalsIgnoreCase("superadmin")) {
//                RolesEntity role2 = dataService.findRoleById(2);
//                roles.add(role2);
//            }
            //Adds privilege to user
            userEntity.setRoles(roles);
            userEntity.setProfileId(profileId);
            UserEntity savedUser = dataService.saveUser(userEntity);
            //Call OTP Service
            otpService.generateOTP(savedUser);
            AgentInfoEntity agentInfoEntity = modelMapper.map(agentRequestDTO,AgentInfoEntity.class);
            dataService.saveAgent(agentInfoEntity);
            UserProfileDTO userProfileDTO = modelMapper.map(savedUser, UserProfileDTO.class);
            return utilities.successResponse("Successfully registered user", userProfileDTO);
        } catch (Exception psqlException) {
            log.error("Caught an exception", psqlException);
            UserEntity userEntity = dataService.findByEmail(agentRequestDTO.getEmail()).get();
            UserProfileDTO userProfileDTO = modelMapper.map(userEntity, UserProfileDTO.class);
            return utilities.failedResponse(205, "Email already exists", userProfileDTO);
        }



    }

    public ResponseDTO activateAccount(ActivateAccDTO activateAccDTO) {

        UserEntity userEntity = dataService.findByEmail(activateAccDTO.getEmail()).get();
        if (userEntity != null) {
            boolean isOtpVerified = otpService.verifyOtp(userEntity.getUserId(), activateAccDTO.getOtp());
            log.info("Check if otp is verified: {}", isOtpVerified);
            if (isOtpVerified) {
                userEntity.setStatus(true);
                userEntity.setPassword(passwordEncoder.encode(activateAccDTO.getPassword()));
                dataService.saveUser(userEntity);
                return utilities.successResponse("Your account has been activated successfully, proceed to login", null);
            }
        }
        return utilities.failedResponse(401, "Incorrect otp", null);

    }


    public ResponseDTO addAuthority(AddAuthDto addAuthDto) {
        UserEntity userEntity = dataService.findByEmail(addAuthDto.getEmail()).get();
        if (userEntity != null) {
            Collection<RolesEntity> roles = userEntity.getRoles();
            RolesEntity role = dataService.findRoleById(addAuthDto.getRoleId());
            roles.add(role);
            userEntity.setRoles(roles);
            dataService.saveUser(userEntity);
            return utilities.successResponse("Successfully added authority", null);

    }
        return utilities.failedResponse(400,"cant add authority",null);

    }

    public ResponseDTO createProfile(ProfileDto profileDto){
        ProfileEntity profile = modelMapper.map(profileDto, ProfileEntity.class);
        if(dataService.findByProfile(profileDto.getProfile()) == null){
            dataService.saveProfile(profile);
            return utilities.successResponse("created profile", profile);
        }
        return utilities.failedResponse(400, "profile exists", null);
    }


}
