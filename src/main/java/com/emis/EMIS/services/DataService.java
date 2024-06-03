package com.emis.EMIS.services;

import com.emis.EMIS.models.*;
import com.emis.EMIS.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataService {
    private final UserRepo userRepo;
    private final OTPRepo otpRepo;
    private final RolesRepo rolesRepo;
    private final ProfileRepo profileRepo;
    private final AgentRepo agentRepo;
    private final PartnerRepo partnerRepo;
    private final SchoolRepo schoolRepo;
    private final SchoolAdminRepo schoolAdminRepo;
    private final UserRoleRepo userRoleRepo;

    public UserEntity saveUser(UserEntity userEntity) {
        log.info("Just about to save a user :: {}",userEntity);
        return userRepo.save(userEntity);
    }

    public Optional<UserEntity> findByEmail(String username) {
        return userRepo.findByEmail(username);
    }
    public void saveOTP(OTPEntity otpEntity){
        otpRepo.save(otpEntity);
    }
    public OTPEntity findOTPByUserId(int userId){
        return otpRepo.findByUserEntityUserId(userId).orElseThrow();
    }
    public UserEntity findByUserId(int userId){
        return userRepo.findByUserId(userId);
    }
    public UserEntity savePassword(UserEntity userEntity){
        return userRepo.save(userEntity);
    }
    public RolesEntity findRoleById(int roleId){
        return rolesRepo.findByRoleId(roleId);
    }
    public ProfileEntity findByProfile(String profile){
        return profileRepo.findByProfile(profile);
    }
    public ProfileEntity saveProfile(ProfileEntity profileEntity){
        return profileRepo.save(profileEntity);}

    public AgentInfoEntity saveAgent(AgentInfoEntity agentInfo){
        return agentRepo.save(agentInfo);
    }
    public PartnerInfoEntity savePartner(PartnerInfoEntity partnerInfo){
        return partnerRepo.save(partnerInfo);
    }
    public SchoolsEntity saveSchool(SchoolsEntity schools){
        return schoolRepo.save(schools);
    }
    public SchoolAdminInfoEntity saveSchoolAdmin(SchoolAdminInfoEntity schoolAdminInfo){
        return schoolAdminRepo.save(schoolAdminInfo);
    }
    public List<ProfileEntity> fetchAll(){
        return profileRepo.findAll();
    }
    public UserRoleEntity saveUserRole(UserRoleEntity userRole){
        return userRoleRepo.save(userRole);
    }
    public List <UserRoleEntity>findByUserId2(int userId){
        return userRoleRepo.findByUserId(userId);
    }
}
