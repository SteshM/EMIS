package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
    private final SystemAdminRepo otherAdminsRepo;
    private final StudentsRepo studentsRepo;
    private final TeachersRepo teachersRepo;
    private final GuardianRepo guardianRepo;
    private final SchoolTypeRepo schoolTypeRepo;
    private final SchoolGenderRepo schoolGenderRepo;
    private final CategoryRepo categoryRepo;
    private final DesignationRepo designationRepo;
    private final DioceseRepo dioceseRepo;
    private final SchoolContactsRepo  schoolContactsRepo;
    private final MenuCodesRepo menuCodesRepo;
    private final DocumentTypesRepo  documentTypesRepo;
    private final IdentityTypeRepo  identityTypeRepo;
    private final SupportingDocumentsRepo  supportingDocumentsRepo;
    private final SchoolDocumentRepo  schoolDocumentRepo;
    private final DirectorsDocsRepo directorsDocsRepo;
    private final DirectorsRepo directorsRepo;
    private final LevelsRepo levelsRepo;
    private final SubjectRepo subjectRepo;
    private final CountyRepo countyRepo;
    private final SubCountyRepo subCountyRepo;
    private final CurriculumRepo curriculumRepo;



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
    public Page<AgentInfoEntity> fetchActiveAgents(Pageable pageable){
        return agentRepo.findByStatus(Status.ACTIVE, pageable);
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

    public Page<PartnerInfoEntity> fetchActivePartners(Pageable pageable) {
        return partnerRepo.findByStatus(Status.ACTIVE,pageable);
    }
    public PartnerInfoEntity findByPartnerId(int partnerId){
        return partnerRepo.findByPartnerId(partnerId);
    }
    public void saveSystemAdmin(SystemAdminEntity systemAdmin){
        otherAdminsRepo.save(systemAdmin);
    }
    public List<SystemAdminEntity>viewAll(){
        return otherAdminsRepo.findByStatus(Status.ACTIVE);
    }
    public SystemAdminEntity findByAdminId(int adminId){
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


    public void saveStudent(StudentEntity student){
        studentsRepo.save(student);

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

    public void saveGuardian(GuardianEntity guardian) {
        guardianRepo.save(guardian);
    }
    public  List<GuardianEntity> fetchActiveGuardians(){
        return guardianRepo.findByStatus(Status.ACTIVE);
    }
    public GuardianEntity findByGuardianId(int guardianId){
        return guardianRepo.findByGuardianId(guardianId);
    }

    public SchoolType saveSchoolType(SchoolType schoolType) {
         return schoolTypeRepo.save(schoolType);
    }

    public List<SchoolType>fetchSchoolTypes(){
        return schoolTypeRepo.findAll();
    }
    public SchoolType findBySchoolTypeId(int schoolTypeId){
        return schoolTypeRepo.findBySchoolTypeId(schoolTypeId);

    }   public void saveSchoolGender(SchoolGender schoolGender) {
        schoolGenderRepo.save(schoolGender);
    }

    public List<SchoolGender>fetchSchoolGenders(){
        return schoolGenderRepo.findAll();
    }
    public SchoolGender findBySchoolGenderId(int schoolGenderId){
        return schoolGenderRepo.findBySchoolGenderId(schoolGenderId);
    }

    public void saveCurriculum(CurriculumEntity curriculum){
        curriculumRepo.save(curriculum);
    }
    public List<CurriculumEntity>fetchCurriculums(){
        return curriculumRepo.findAll();
    }
    public CurriculumEntity findByCurriculumId(int curriculumId){
        return curriculumRepo.findByCurriculumId(curriculumId);
    }
    public void saveCounty(CountyEntity countyEntity){
        countyRepo.save(countyEntity);
    }
    public CountyEntity findByCountyId(int countyId){
        return countyRepo.findByCountyId(countyId);
    }
    public List<CountyEntity>fetchAllCounties(){
        return countyRepo.findAll();
    }

    public void saveSubCounty(SubCountyEntity subCounty){
        subCountyRepo.save(subCounty);
    }
    public SubCountyEntity findBySubCountyId(int subCountyId){
        return subCountyRepo.findBySubCountyId(subCountyId);
    }
    public List<SubCountyEntity>fetchAllSubCounties(){
        return subCountyRepo.findAll();
    }
    public void saveCategories(CategoriesEntity categoriesEntity){
        categoryRepo.save(categoriesEntity);
    }
    public CategoriesEntity findByCategoryId(int categoryId){
        return categoryRepo.findByCategoryId(categoryId);
    }
    public List<CategoriesEntity>fetchAllCategories(){
        return categoryRepo.findAll();
    }
    public void saveDesignation(DesignationEntity designationEntity){
        designationRepo.save(designationEntity);
    }
    public DesignationEntity findByDesignationId(int designationId){
        return designationRepo.findByDesignationId(designationId);
    }

    public List<DesignationEntity>fetchDesignations(){
        return designationRepo.findAll();
    }
    public void saveDiocese(DioceseEntity dioceseEntity){
        dioceseRepo.save(dioceseEntity);
    }
    public DioceseEntity findByDioceseId(int dioceseId){
        return dioceseRepo.findByDioceseId(dioceseId);
    }
    public List<DioceseEntity>fetchDioceses(){
        return dioceseRepo.findAll();
    }

    public void saveSchoolContacts(SchoolContacts schoolContacts){
        schoolContactsRepo.save(schoolContacts);

    }
    public List<SchoolContacts> fetchSchoolContacts(){
        return schoolContactsRepo.findAll();
    }
    public SchoolContacts findBySchoolContactsId(int schoolContactId){
        return schoolContactsRepo.findBySchoolContactId(schoolContactId);
    }

    public void saveMenuCodes(MenuCodes menuCodes){
        menuCodesRepo.save(menuCodes);

    }

    public List<MenuCodes> fetchAllMenuCodes(){
        return menuCodesRepo.findAll();
    }

    public MenuCodes findByMenuCodeId(int menuCodeId){
        return menuCodesRepo.findByMenuCodeId(menuCodeId);
    }



    public void saveDocumentTypes(DocumentTypes documentTypes){
        documentTypesRepo.save(documentTypes);

    }

    public List<DocumentTypes> fetchAllDocumentTypes(){
        return documentTypesRepo.findAll();
    }

    public DocumentTypes findByDocumentTypeId(int documentTypeId){
        return documentTypesRepo.findByDocumentTypeId(documentTypeId);
    }

    public void saveIdentityType(IdentityType identityType){
        identityTypeRepo.save(identityType);
    }

    public List<IdentityType>FetchAllIdentityTypes(){
        return identityTypeRepo.findAll();
    }
    public IdentityType findByIdentityTypeId(int identityTypeId){
        return identityTypeRepo.findByIdentityTypeId(identityTypeId);
    }



    public void saveSupportDocs(SupportingDocuments supportingDocuments){
        supportingDocumentsRepo.save(supportingDocuments);
    }

    public List<SupportingDocuments>FetchAllSupportingDocs(){
        return supportingDocumentsRepo.findAll();
    }

    public SupportingDocuments findBySupportDocId(int supportDocId){
        return supportingDocumentsRepo.findBySupportDocId(supportDocId);
    }

    public SchoolDocuments findBySchoolDocId(int schoolDocId){
       return schoolDocumentRepo.findBySchoolDocId(schoolDocId);

    }

    public void saveSchoolDocument(SchoolDocuments schoolDocuments){
        schoolDocumentRepo.save(schoolDocuments);
    }

    public void saveNewDocument(SchoolDocuments newDocument) {
        schoolDocumentRepo.save(newDocument);
    }

    public void saveDirectorsDocument(DirectorsDocsEntity directorsDocsEntity){
        directorsDocsRepo.save(directorsDocsEntity);
    }

    public DirectorsDocsEntity findByDirectorDocId(int directorId){
        return directorsDocsRepo.findByDirectorDocId(directorId);
    }

    public void saveDirector(DirectorsEntity directorsEntity){
        directorsRepo.save(directorsEntity);
}
  public List<DirectorsEntity>FetchAllDirectors(){
        return directorsRepo.findAll();
  }
  public DirectorsEntity findByDirectorId(int directorId){
        return directorsRepo.findByDirectorId(directorId);
  }
  public void saveLevel(LevelsEntity levelsEntity){
      levelsRepo.save(levelsEntity);
  }

  public List <LevelsEntity> fetchAllLevels() {
      return levelsRepo.findAll();
  }
  public LevelsEntity findByLevelId(int levelId){
        return levelsRepo.findByLevelId(levelId);
  }

//    public LevelsEntity findByCurriculumIdAndLevelId(int curriculumId,int levelId){
//        return levelsRepo.findByCurriculumIdAndLevelId(curriculumId,levelId);
//  }

}
