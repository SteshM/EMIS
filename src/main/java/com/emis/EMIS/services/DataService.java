package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
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
    private final OtherAdminsRepo otherAdminsRepo;
    private final StudentsRepo studentsRepo;
    private final TeachersRepo teachersRepo;


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
        return otpRepo.findByUserId(userId);
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

    }  public ProfileEntity findById(int profileId){
       return profileRepo.findByProfileId(profileId);
    }

    public void saveProfile(ProfileEntity profileEntity){
        profileRepo.save(profileEntity);}

    public void saveAgent(AgentInfoEntity agentInfo){
        log.info("About to insert Agent:{}", agentInfo);
        agentRepo.save(agentInfo);
    }
    public void savePartner(PartnerInfoEntity partnerInfo){
         partnerRepo.save(partnerInfo);
    }
    public void saveSchool(SchoolsEntity schools){
        schoolRepo.save(schools);
    }
    public void saveSchoolAdmin(SchoolAdminInfoEntity schoolAdminInfo){
        schoolAdminRepo.save(schoolAdminInfo);
    }
    public List<ProfileEntity> fetchAll(){
        return profileRepo.findAll();
    }
    public  void saveUserRole(UserRoleEntity userRole)
    {userRoleRepo.save(userRole);
    }
    public List <UserRoleEntity>findByUserId2(int userId){
        return userRoleRepo.findByUserId(userId);
    }
    public List<SchoolAdminInfoEntity>fetchActiveSchoolAdmins(){
        return schoolAdminRepo.findByStatus(Status.ACTIVE);
    }
    public SchoolAdminInfoEntity findBySchoolAdminId(int schoolAdminId){
        return schoolAdminRepo.findBySchoolAdminId(schoolAdminId);
    }

    public List <AgentInfoEntity> fetchAgents(){
        return agentRepo.findAll();
    }
    public List <AgentInfoEntity> fetchActiveAgents(){
        return agentRepo.findByStatus(Status.ACTIVE);
    }
    public AgentInfoEntity findByAgentId(int agentId){
        return agentRepo.findByAgentId(agentId);
    }
    public List <SchoolsEntity>findAll(){
        return schoolRepo.findAll();
    }
    public SchoolsEntity findBySchoolId(int schoolId){
        return schoolRepo.findBySchoolId(schoolId);
    }

    public List<PartnerInfoEntity> fetchActivePartners() {
        return partnerRepo.findByStatus(Status.ACTIVE);
    }
    public PartnerInfoEntity findByPartnerId(int partnerId){
        return partnerRepo.findByPartnerId(partnerId);
    }
    public void saveOtherAdmin(OtherAdminEntity otherAdmin){
        otherAdminsRepo.save(otherAdmin);
    }
    public List<OtherAdminEntity>viewAll(){
        return otherAdminsRepo.findByStatus(Status.ACTIVE);
    }
    public OtherAdminEntity findByAdminId(int adminId){
        return otherAdminsRepo.findByAdminId(adminId);
    }
    public RolesEntity findByProfileId(int profileId){
        return (RolesEntity) rolesRepo.findByProfileId(profileId);
    }
    public StudentEntity findByStudentId(int studentId){
        return studentsRepo.findByStudentId(studentId);
    }
    public List<StudentEntity>viewAllStudents(){
        return studentsRepo.findByStatus(Status.ACTIVE);
    }


    public StudentEntity saveStudent(StudentEntity student){
        return studentsRepo.save(student);

    }

    public void saveTeacher(TeacherEntity teacher) {
        teachersRepo.save(teacher);
    }
    public  List <TeacherEntity> fetchActiveTeachers(){
        return teachersRepo.findByStatus(Status.ACTIVE);
    }
    public TeacherEntity findByTeacherId(int teacherId){
        return teachersRepo.findByTeacherId(teacherId);
    }
}
