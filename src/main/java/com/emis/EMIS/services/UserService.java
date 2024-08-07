package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.ImageUploader;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Stella
 * user Service
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final DataService dataService;
    private final Utilities utilities;
    private final OTPService otpService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final ImageUploader imageUploader;

    @Value("${profile.images.path}")
    private String profilePath;

    /**
     *
     * @param username username
     * @return response dto
     */

    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> credential = dataService.findByEmail(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRoleEntity> userRoles = dataService.findByUserId2(credential.get().getUserId());

        for (UserRoleEntity userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(dataService.findRoleById(userRole.getRoleId()).getRole()));
        }

        return new User(credential.get().getEmail(), credential.get().getPassword(), authorities);
    }


    /**
     * This is a method to register users in the system
     * @param userDTO is the request dto
     * @return Response dto
     */
    public ResponseDTO
    register(UserDTO userDTO) {

        try {
            var userEntity = modelMapper.map(userDTO, UserEntity.class);
            UserEntity savedUser = dataService.saveUser(userEntity);
            otpService.generateOTP(savedUser);
            userEntity.setStatus(Status.ACTIVE);
            int profileId = userDTO.getProfileId();
//SystemAdmin
             if(profileId ==1) {
                 var systemAdmin = modelMapper.map(userDTO, SystemAdminEntity.class);
                 systemAdmin.setStatus(Status.ACTIVE);
                 systemAdmin.setUserEntity(savedUser);
                 dataService.saveSystemAdmin(systemAdmin);
                 return utilities.successResponse("Created other Admin", null);
//SchoolAdmin
             }else if(profileId ==2){
                var schoolAdminInfo = modelMapper.map(userDTO,SchoolAdminInfoEntity.class);
                schoolAdminInfo.setStatus(Status.ACTIVE);
                schoolAdminInfo.setUserEntity(savedUser);
                dataService.saveSchoolAdmin(schoolAdminInfo);
                return utilities.successResponse("Created a school Admin",null);
//Agents
            }else  if (profileId == 3){
                var agentInfo = modelMapper.map(userDTO, AgentInfoEntity.class);
                agentInfo.setUserEntity(savedUser);
                agentInfo.setStatus(Status.ACTIVE);
                dataService.saveAgent(agentInfo);
                return utilities.successResponse("Registered agent",null);
//Partner
            }else if (profileId == 4){
                var partnerInfo = modelMapper.map(userDTO, PartnerInfoEntity.class);
                 savedUser.setStatus(Status.ACTIVE);
                 partnerInfo.setUserEntity(savedUser);
                partnerInfo.setStatus(Status.ACTIVE);
                partnerInfo.setEducationalResource(dataService.findByResourceId(userDTO.getResourceId()));
                dataService.savePartner(partnerInfo);
                return utilities.successResponse("Created a partner",null);
//Student
            }else if (profileId == 5){
                var student = modelMapper.map(userDTO, StudentEntity.class);
                savedUser.setStatus(Status.ACTIVE);
                log.info("status : {}",Status.INACTIVE);
                student.setUser(savedUser);
                student.setSchools(dataService.findBySchoolId(userDTO.getSchoolId()));
                student.setStatus(Status.ACTIVE);
                log.info("about to save a student :{}",student);
                dataService.saveStudent(student);
                return utilities.successResponse("Created a student",null);
//Teacher
            }else if (profileId == 6){
                var teacher = modelMapper.map(userDTO, TeacherEntity.class);
                teacher.setUser(savedUser);
                teacher.setStatus(Status.ACTIVE);
                teacher.setSchool(dataService.findBySchoolId(userDTO.getSchoolId()));
                log.info("about to save a teacher :{}",teacher);
                dataService.saveTeacher(teacher);
                return utilities.successResponse("Created a teacher",null);
//Guardian
            }else if (profileId == 7){
                var guardian = modelMapper.map(userDTO, GuardianEntity.class);
                guardian.setUserEntity(savedUser);
                guardian.setStatus(Status.ACTIVE);
                dataService.saveGuardian(guardian);
                return utilities.successResponse("Created a guardian",null);

            }

            //adding basic role-->standard
            RolesEntity role = dataService.findRoleById(1);
           var userRoleEntity  = new UserRoleEntity();
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
            var userEntity = dataService.findByEmail(userDTO.getEmail()).get();
            var userProfileDTO = modelMapper.map(userEntity, UserProfileDTO.class);
            return utilities.failedResponse(205, "Email already exists", userProfileDTO);
        }



    }

    /**
     * A method to activate the account after registering and in case one forgets their password
     * @param activateAccDTO the request dto
     * @return response dto
     */

    public ResponseDTO activateAccount(ActivateAccDTO activateAccDTO) {

        var userEntity = dataService.findByEmail(activateAccDTO.getEmail()).get();
        if (userEntity != null) {
            log.info("the user = ,{}", userEntity);
            boolean isOtpVerified = otpService.verifyOtp(userEntity.getUserId(), activateAccDTO.getOtp());
            log.info("Check if otp is verified: {}", isOtpVerified);
            if (isOtpVerified) {
                userEntity.setStatus(Status.ACTIVE);
                userEntity.setPassword(passwordEncoder.encode(activateAccDTO.getPassword()));
                dataService.saveUser(userEntity);
                return utilities.successResponse("Your account has been activated successfully, proceed to login", null);
            }
        }
        return utilities.failedResponse(401, "Incorrect otp", null);

    }



    private String email(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    /**
     * A method to upload/update the profile pic
     * @param file file
     * @return response dto
     */
    public ResponseDTO updateProfilePic(MultipartFile file){
        var user= dataService.findByEmail(this.email()).get();
//        if(user.getProfilePic() != null){
//            imageUploader.deleteImg(profilePath+"/"+user.getProfilePic());
//        }
//
//        user.setProfilePic(imageUploader.uploadImage(profilePath, file));
        var user1 = dataService.saveUser(user);
        return utilities.successResponse("updated", user1);
    }

//
//        public ResponseDTO addAuthority(AddAuthDto addAuthDto) {
//        ProfileEntity profile = dataService.findByProfileId2(addAuthDto.getProfileId());
//          if (profile  != null) {
//              RolesEntity role = dataService.findRoleById(addAuthDto.getRoleId());
//              Collection<RolesEntity> roles = profile.getRoles();
//            roles.add(role);
//            profile.setRoles(roles);
//            dataService.saveProfile(profile);
//            return utilities.successResponse("Successfully added authority", null);
//
//    }
//        return utilities.failedResponse(400,"cant add authority",null);
//
//    }

}
