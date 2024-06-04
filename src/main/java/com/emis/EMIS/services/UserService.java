package com.emis.EMIS.services;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final DataService dataService;
    private final Utilities utilities;
    private final OTPService otpService;
    private final PasswordEncoder passwordEncoder;
    private final UserConfigs userConfigs;
    private final ModelMapper modelMapper;

    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> credential = dataService.findByEmail(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRoleEntity> userRoles = dataService.findByUserId2(credential.get().getUserId());

        for (UserRoleEntity userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(dataService.findRoleById(userRole.getRoleId()).getRole()));
        }

        return new User(credential.get().getEmail(), credential.get().getPassword(), authorities);
    }


    public ResponseDTO register(UserDTO userDTO) {

        try {
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            UserEntity savedUser = dataService.saveUser(userEntity);
            otpService.generateOTP(savedUser);
            userEntity.setStatus(userConfigs.getInactiveStatus());
            int profileId = userDTO.getProfileId();

            if (profileId == 0) {
                return utilities.failedResponse(400, "Profile does not exist", null);

            }else if (profileId == 1){
                SchoolsEntity  schools = modelMapper.map(userDTO,SchoolsEntity.class);
                dataService.saveSchool(schools);
                return utilities.successResponse("registered a school",null);

            }else if(profileId ==2){
                SchoolAdminInfoEntity schoolAdminInfo = modelMapper.map(userDTO,SchoolAdminInfoEntity.class);
                dataService.saveSchoolAdmin(schoolAdminInfo);
                return utilities.successResponse("Created a school Admin",null);

            }else  if (profileId == 3){
                AgentInfoEntity agentInfo = modelMapper.map(userDTO, AgentInfoEntity.class);
                agentInfo.setUserEntity(savedUser);
                dataService.saveAgent(agentInfo);
                return utilities.successResponse("Registered agent",null);

            }else  if(profileId == 4){
                PartnerInfoEntity partnerInfo = modelMapper.map(userDTO, PartnerInfoEntity.class);
                dataService.savePartner(partnerInfo);
                return utilities.successResponse("Created a partner",null);

            }

            //adding basic role-->standard
            RolesEntity role = dataService.findRoleById(1);
           UserRoleEntity userRoleEntity  = new UserRoleEntity();
           if(role == null){
               return utilities.failedResponse(205,"This role does not exist ",null);
           }
            userEntity.setProfileId(profileId);
            savedUser = dataService.saveUser(userEntity);
            userRoleEntity.setUserId(savedUser.getUserId());
            userRoleEntity.setRoleId(1);
            dataService.saveUserRole(userRoleEntity);
            //Call OTP Service
            otpService.generateOTP(savedUser);

            UserProfileDTO userProfileDTO = modelMapper.map(savedUser, UserProfileDTO.class);
            return utilities.successResponse("Successfully registered user", userProfileDTO);
        } catch (Exception psqlException) {
            log.error("Caught an exception", psqlException);
            UserEntity userEntity = dataService.findByEmail(userDTO.getEmail()).get();
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
                userEntity.setStatus(userConfigs.getActiveStatus());
                userEntity.setPassword(passwordEncoder.encode(activateAccDTO.getPassword()));
                dataService.saveUser(userEntity);
                return utilities.successResponse("Your account has been activated successfully, proceed to login", null);
            }
        }
        return utilities.failedResponse(401, "Incorrect otp", null);

    }


//    public ResponseDTO addAuthority(AddAuthDto addAuthDto) {
//        UserEntity userEntity = dataService.findByEmail(addAuthDto.getEmail()).get();
//        if (userEntity != null) {
//            Collection<RolesEntity> roles = userEntity.getRoles();
//            RolesEntity role = dataService.findRoleById(addAuthDto.getRoleId());
//            roles.add(role);
//            userEntity.setRoles(roles);
//            dataService.saveUser(userEntity);
//            return utilities.successResponse("Successfully added authority", null);
//
//    }
//        return utilities.failedResponse(400,"cant add authority",null);
//
//    }

    public ResponseDTO createProfile(ProfileDto profileDto){
        ProfileEntity profile = modelMapper.map(profileDto, ProfileEntity.class);
        if(dataService.findByProfile(profileDto.getProfile()) == null){
            dataService.saveProfile(profile);
            return utilities.successResponse("created profile", profile);
        }
        return utilities.failedResponse(400, "profile exists", null);
    }


    public ResponseDTO fetchAll() {
        List<ProfileEntity>profiles = dataService.fetchAll();
        return utilities.successResponse("fetched all profiles",profiles);
    }

    public ResponseDTO fetchByProfile(String profile) {
        ProfileEntity profileEntity = dataService.findByProfile(profile);
        return utilities.successResponse("fetched profile",profile);
    }
}
