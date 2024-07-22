package com.emis.EMIS.services;
import com.emis.EMIS.enums.RemarksClarificationStatus;
import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.AuthenticatedUser;
import com.emis.EMIS.utils.FileUpload;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {
 private final ModelMapper modelMapper;
 private final DataService dataService;
 private final Utilities utilities;
 private final FileUpload fileUpload;
 private final EmailService emailService;


    @Value("${logo.images.path}")
    String logoPath;

    @Value("${school.doc.images.path}")
    String docPath;

    /**
     *
     * @param schoolDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO createBasicInfo(SchoolDTO schoolDTO) throws JsonProcessingException {
        var school = modelMapper.map(schoolDTO,SchoolsEntity.class);
        school.setCategoriesEntity(dataService.findByCategoryId(schoolDTO.getCategoryId()));
        school.setCurriculum(dataService.findByCurriculumId(schoolDTO.getCurriculumId()));
        school.setSchoolType(dataService.findBySchoolTypeId(schoolDTO.getSchoolTypeId()));
        school.setSchoolGender(dataService.findBySchoolGenderId(schoolDTO.getSchoolGenderId()));
        log.info("About to save a school's basic info:{}", new ObjectMapper().writeValueAsString(school));
        dataService.saveSchool(school);
        return utilities.successResponse("Created school",schoolDTO);
    }

    public ResponseDTO createBasicInfoWithFile(String schoolFormData, MultipartFile logo) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        SchoolDTO schoolDTO = objectMapper.readValue(schoolFormData,SchoolDTO.class);
        log.info("creating school basic info :{}",schoolDTO.toString());
        String fileName = fileUpload.uploadImage(logoPath,logo);
        schoolDTO.setLogo(fileName);
        return createBasicInfo(schoolDTO);
    }

    public ResponseDTO createBasicInfoWithoutFile(String schoolFormData) throws JsonProcessingException {
        SchoolDTO schoolDTO = modelMapper.map(schoolFormData, SchoolDTO.class);
        return createBasicInfo(schoolDTO);
    }

    /**
     * A method to view all active schools in the system
     * @return response dto
     */

    public ResponseDTO viewSchools() throws JsonProcessingException {
        List<SchoolsEntity>schoolsEntities = dataService.findAll();
        log.info("About to fetch schools {}",schoolsEntities);
        List<SchoolsResDTO>schoolsResDTOS = schoolsEntities.stream()
                .map(schools -> {

                    return SchoolsResDTO.builder()
                            .schoolId(schools.getSchoolId())
                            .schoolName(schools.getSchoolName())
                            .schoolType(schools.getSchoolType()==null?"":schools.getSchoolType().getName())
                            .schoolTypeId(schools.getSchoolType() == null?0:schools.getSchoolType().getSchoolTypeId())
                            .schoolGenderId(schools.getSchoolGender() == null?0:schools.getSchoolGender().getSchoolGenderId())
                            .schoolGender(schools.getSchoolGender() == null?"": schools.getSchoolGender().getName())
                            .curriculum(schools.getCurriculum()==null?"":schools.getCurriculum().getCurriculum())
                            .curriculumId(schools.getCurriculum()==null?0:schools.getCurriculum().getCurriculumId())
                            .category(schools.getCategoriesEntity()==null?"":schools.getCategoriesEntity().getCategory())
                            .categoryId(schools.getCategoriesEntity()==null?0:schools.getCategoriesEntity().getCategoryId())
                            .logo(schools.getLogo())
                            .emailAddress(schools.getEmailAddress())
                            .postalAddress(schools.getPostalAddress())
                            .postalCode(schools.getPostalCode())
                            .mobileNo(schools.getMobileNo())
                            .moeRegistrationNo(schools.getMoeRegistrationNo())
                            .latitude(schools.getLatitude())
                            .longitude(schools.getLongitude())
                            .subCounty(schools.getSubCounty())
                            .county(schools.getCounty())
                            .build();
                })
                .toList();
        log.info("Fetched  all school Details:{}", new ObjectMapper().writeValueAsString(schoolsEntities));
        return utilities.successResponse("Successfully fetched schools",schoolsResDTOS);

    }

    public ResponseDTO getSchool(int id) throws JsonProcessingException {
        var schools= dataService.findBySchoolId(id);
        log.info("Fetching a school's Details:{}", new ObjectMapper().writeValueAsString(schools));
        var schoolDTO = modelMapper.map(schools, SchoolDTO.class);
        return utilities.successResponse("Successfully fetched a school",schoolDTO);
    }


    /**
     * Fetching a school, updating its details and saving
     * @param id school id
     * @param schoolDTO the school dto
     * @return response dto
     */

    public ResponseDTO updateSchool(int id, SchoolDTO schoolDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var school = dataService.findBySchoolId(id);
        log.info("Fetched a school:{}", objectMapper.writeValueAsString(school));
        modelMapper.map(schoolDTO,school);
        log.info("Updated school Details. About to save:{}", objectMapper.writeValueAsString(school));
        var savedSChool = dataService.saveSchool(school);
        var schoolResDTO = modelMapper.map(savedSChool,SchoolsResDTO.class);
        return utilities.successResponse("Successfully updated school details",schoolResDTO);
    }

    /**
     * soft deleting a school
     * @param id schoolId
     * @return response dto
     */

    public ResponseDTO deleteSchool(int id) {
        var school = dataService.findBySchoolId(id);
        school.setStatus(Status.DELETED);
        dataService.saveSchool(school);
        return utilities.successResponse("deleted school",null);
    }


    /**
     *
     * @param schoolTypeName school type
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addSchoolType(String schoolTypeName){
       SchoolType schoolType = new SchoolType();
       schoolType.setName(schoolTypeName);
        log.info("About to save a schoolType basic info:{}",schoolType);
       var savedSchoolTYpe = dataService.saveSchoolType(schoolType);
       var schoolTypeDTO =modelMapper.map(savedSchoolTYpe,SchoolTypeDTO.class);
        return utilities.successResponse("added a school type",schoolTypeDTO);


    }

    public ResponseDTO getAllSchoolTypes() throws JsonProcessingException {
        List<SchoolType>schoolTypeList = dataService.fetchSchoolTypes();
        log.info("About to fetch all school types from the db::{}",schoolTypeList);
        List<SchoolTypeDTO>schoolTypeDTOList = schoolTypeList.stream()
                .map(schoolType -> {
                    return modelMapper.map(schoolType,SchoolTypeDTO.class);
                })
                .toList();
        log.info("Fetched  all  schoolType Details:{}", new ObjectMapper().writeValueAsString(schoolTypeList));
return utilities.successResponse("fetched all school types",schoolTypeDTOList);


    }

    public ResponseDTO updateSchoolType(int id, SchoolTypeDTO schoolTypeDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        SchoolType schoolType = dataService.findBySchoolTypeId(id);
        log.info("Fetched a school type:{}", objectMapper.writeValueAsString(schoolType));
        modelMapper.map(schoolTypeDTO,schoolType);
        log.info("Updated school type Details. About to save:{}", objectMapper.writeValueAsString(schoolType));
        dataService.saveSchoolType(schoolType);
        return utilities.successResponse("updated school type successfully",schoolTypeDTO);


    }

    /**
     * CRUD
     * SCHOOL GENDER
     * @param schoolGenderDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addSchoolGender(SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        var schoolGender = modelMapper.map(schoolGenderDTO,SchoolGender.class);
        log.info("About to save a schoolGender basic info:{}", new ObjectMapper().writeValueAsString(schoolGender));
        dataService.saveSchoolGender(schoolGender);
        return utilities.successResponse("added a school gender",schoolGenderDTO);
    }

    public ResponseDTO getAllSchoolGenders() throws JsonProcessingException {
        List<SchoolGender>schoolGenderList = dataService.fetchSchoolGenders();
        log.info("About to fetch all school genders from the db::{}",schoolGenderList);
        List<SchoolGenderResDTO>schoolGenderResDTOS = schoolGenderList.stream()
                .map(schoolGender -> {
                    return modelMapper.map(schoolGender,SchoolGenderResDTO.class);
                })
                .toList();
        log.info("Fetched  all  school gender Details:{}", new ObjectMapper().writeValueAsString(schoolGenderList));
        return utilities.successResponse("fetched all school genders",schoolGenderResDTOS);
    }

    public ResponseDTO updateSchoolGender(int id, SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        SchoolGender schoolGender = dataService.findBySchoolGenderId(id);
        log.info("Fetched a school gender:{}", objectMapper.writeValueAsString(schoolGender));
        modelMapper.map(schoolGender,schoolGenderDTO);
        log.info("Updated school gender Details. About to save:{}", objectMapper.writeValueAsString(schoolGender));
        dataService.saveSchoolGender(schoolGender);
        return utilities.successResponse("updated school gender successfully",schoolGenderDTO);
    }



    /**
     * CURRICULUM
     * @param curriculumDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO addCurriculum(CurriculumDTO curriculumDTO) throws JsonProcessingException {
        CurriculumEntity curriculum = modelMapper.map(curriculumDTO,CurriculumEntity.class);
        log.info("About to save a curriculum basic info:{}", new ObjectMapper().writeValueAsString(curriculum));
        var savedCurriculum = dataService.saveCurriculum(curriculum);
        var curriculumResDTO = modelMapper.map(savedCurriculum, CurriculumResDTO.class);
        return utilities.successResponse("added a curriculum",curriculumResDTO);
    }

    public ResponseDTO getCurriculums() throws JsonProcessingException {
        List<CurriculumEntity>curriculumEntityList = dataService.fetchCurriculums();
        log.info("About to fetch all curriculums from the db::{}",curriculumEntityList);
        List<CurriculumResDTO>curriculumResDTOList = curriculumEntityList.stream()
                .map(curriculum -> {
                    return modelMapper.map(curriculum,CurriculumResDTO.class);
                })
                .toList();
        log.info("Fetched  all  curriculum Details:{}", new ObjectMapper().writeValueAsString(curriculumEntityList));
        return utilities.successResponse("fetched all curriculums",curriculumResDTOList);
    }

    public ResponseDTO updateCurriculum(int id, CurriculumDTO curriculumDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        CurriculumEntity curriculum = dataService.findByCurriculumId(id);
        log.info("Fetched a curriculum from the db:{}", objectMapper.writeValueAsString(curriculum));
        curriculum.setCurriculum(curriculumDTO.getCurriculum());
        log.info("Updated curriculum Details. About to save:{}", objectMapper.writeValueAsString(curriculum));
        var updatedCurriculum = dataService.saveCurriculum(curriculum);
        var curriculumResDTO =modelMapper.map(updatedCurriculum,CurriculumResDTO.class);
        return utilities.successResponse("updated school type successfully",curriculumResDTO);
    }
    public ResponseDTO deleteCurriculum(int id) {
        CurriculumEntity curriculum = dataService.findByCurriculumId(id);
        curriculum.setStatus(Status.DELETED);
       dataService.saveCurriculum(curriculum);
       return utilities.successResponse("Successfully deleted a curriculum",null);

    }


    /**
     * CATEGORY
     * @param categoryDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */


    public ResponseDTO addCategory(CategoryDTO categoryDTO) throws JsonProcessingException {
        var categoriesEntity = new CategoriesEntity();
        categoriesEntity.setCategory(categoryDTO.getCategory());
//        dataService.findBySchoolId(categoryDTO.getSchoolId());
        log.info("About to save a category:{}", new ObjectMapper().writeValueAsString(categoriesEntity));
        var savedCategory = dataService.saveCategories(categoriesEntity);
        var categoryResDTO = modelMapper.map(savedCategory, CategoryResDTO.class);
        return utilities.successResponse("Added a category",categoryResDTO);

    }

    public ResponseDTO updateCategory(CategoryDTO categoryDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var categoriesEntity = dataService.findByCategoryId(id);
        log.info("Fetched a category from the db:{}", objectMapper.writeValueAsString(categoriesEntity));
        modelMapper.map(categoriesEntity,categoryDTO);
        log.info("Updated category Details. About to save:{}", objectMapper.writeValueAsString(categoriesEntity));
        dataService.saveCategories(categoriesEntity);
        return utilities.successResponse("updated category details successfully",categoryDTO);
    }


    public ResponseDTO getCategories() throws JsonProcessingException {
        List<CategoriesEntity>categoriesEntities = dataService.fetchAllCategories();
        List<CategoryResDTO>categoryResDTOS =categoriesEntities.stream()
                .map(categoriesEntity -> {
                    return modelMapper.map(categoriesEntity, CategoryResDTO.class);
                })
                .toList();

        log.info("Fetched  all  categories :{}", new ObjectMapper().writeValueAsString(categoriesEntities));
return utilities.successResponse("fetched all categories",categoryResDTOS);
    }


    /**
     * DESIGNATION
     * @param designationDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addDesignation(DesignationDTO designationDTO) throws JsonProcessingException {
        DesignationEntity designationEntity = modelMapper.map(designationDTO, DesignationEntity.class);
        log.info("About to save a designation:{}", new ObjectMapper().writeValueAsString(designationEntity));
         var savedDesignation = dataService.saveDesignation(designationEntity);
         var designationResDTO = modelMapper.map(savedDesignation,DesignationResDTO.class);
        return utilities.successResponse("Added designation",designationResDTO);

    }
    public ResponseDTO updateDesignation(DesignationDTO designationDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var designationEntity = dataService.findByDesignationId(id);
        log.info("Fetched a designation from the db:{}", objectMapper.writeValueAsString(designationEntity));
        modelMapper.map(designationEntity,designationDTO);
        log.info("Updated designation Details. About to save:{}", objectMapper.writeValueAsString(designationEntity));
        dataService.saveDesignation(designationEntity);
        return utilities.successResponse("updated designation details successfully",designationDTO);
    }


    public ResponseDTO getDesignations() throws JsonProcessingException {
        List<DesignationEntity>designationEntityList = dataService.fetchDesignations();
        List<DesignationResDTO>designationResDTOS = designationEntityList.stream()
                .map(designationEntity -> {
                    return modelMapper.map(designationEntity, DesignationResDTO.class);
                })
                .toList();
        log.info("Fetched  all  designations :{}", new ObjectMapper().writeValueAsString(designationEntityList));

return utilities.successResponse("Fetched all designations",designationResDTOS);
    }


    /**
     * DIOCESE
     * @param dioceseDTO request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addDiocese(DioceseDTO dioceseDTO) throws JsonProcessingException {
        DioceseEntity dioceseEntity = modelMapper.map(dioceseDTO, DioceseEntity.class);
        log.info("About to save a diocese:{}", new ObjectMapper().writeValueAsString(dioceseEntity));
        dataService.saveDiocese(dioceseEntity);
        return utilities.successResponse("saved a diocese",null);

    }

    public ResponseDTO updateDiocese(DioceseDTO dioceseDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var dioceseEntity = dataService.findByDioceseId(id);
        log.info("Fetched a diocese from the db:{}", objectMapper.writeValueAsString(dioceseEntity));
        modelMapper.map(dioceseEntity,dioceseDTO);
        log.info("Updated diocese Details. About to save:{}", objectMapper.writeValueAsString(dioceseEntity));
        dataService.saveDiocese(dioceseEntity);
        return utilities.successResponse("updated diocese details successfully",dioceseDTO);
    }


    public ResponseDTO getDioceses() throws JsonProcessingException {
        List<DioceseEntity>dioceseEntityList = dataService.fetchDioceses();
        List<DioceseDTO>dioceseDTOList = dioceseEntityList.stream()
                .map(dioceseEntity -> {
                    return modelMapper.map(dioceseEntity,DioceseDTO.class);
                })
                .toList();
        log.info("Fetched  all  dioceses :{}", new ObjectMapper().writeValueAsString(dioceseEntityList));
return utilities.successResponse("Fetched all dioceses",dioceseDTOList);
    }


    /**
     * SCHOOL CONTACTS
     * @param schoolContactsDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */


    public ResponseDTO createSchoolContact(SchoolContactsDTO schoolContactsDTO) throws JsonProcessingException {
        SchoolContacts schoolContacts = modelMapper.map(schoolContactsDTO,SchoolContacts.class);
        log.info("About to save a school contacts:{}", new ObjectMapper().writeValueAsString(schoolContacts));
        dataService.saveSchoolContacts(schoolContacts);
        return utilities.successResponse("saved school contacts",null);

    }

    public ResponseDTO updateSchoolContacts(SchoolContactsDTO schoolContactsDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var schoolContacts = dataService.findBySchoolContactsId(id);
        log.info("Fetched school contacts from the db:{}", objectMapper.writeValueAsString(schoolContacts));
        modelMapper.map(schoolContacts,schoolContactsDTO);
        log.info("Updated school contacts  Details. About to save:{}", objectMapper.writeValueAsString(schoolContacts));
        dataService.saveSchoolContacts(schoolContacts);
        return utilities.successResponse("updated school contacts details successfully",schoolContactsDTO);
    }


    public ResponseDTO getSchoolContact(int id) throws JsonProcessingException {
        var schoolContacts= dataService.findBySchoolContactsId(id);
        log.info("Fetching a school's contact Details:{}", new ObjectMapper().writeValueAsString(schoolContacts));
        var schoolContactsDTO = modelMapper.map(schoolContacts, SchoolContactsDTO.class);
        return utilities.successResponse("Successfully fetched a partner",schoolContactsDTO);
    }

    public ResponseDTO viewSchoolContacts() throws JsonProcessingException {
        List<SchoolContacts>schoolContactsList = dataService.fetchSchoolContacts();
        List<SchoolContactResDTO>schoolContactResDTOS = schoolContactsList.stream()
                .map(schoolContacts -> {
                    return modelMapper.map(schoolContacts,SchoolContactResDTO.class);
                })
                .toList();
        log.info("Fetched  all  school contacts :{}", new ObjectMapper().writeValueAsString(schoolContactsList));
        return utilities.successResponse("Fetched all school contacts",schoolContactResDTOS);
    }

    public ResponseDTO deleteSchoolContacts(int id) {
        var schoolContacts = dataService.findBySchoolContactsId(id);
        schoolContacts.setStatus(Status.DELETED);
        dataService.saveSchoolContacts(schoolContacts);
        return utilities.successResponse("deleted school contacts",null);
    }


    /**
     * MENU CODES
     * @param documentTypeCodesDTO request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO saveMenuCode(DocumentTypeCodesDTO documentTypeCodesDTO) throws JsonProcessingException {
        MenuCodes menuCodes = new MenuCodes();
        menuCodes.setName(documentTypeCodesDTO.getName());
        menuCodes.setRemarks(documentTypeCodesDTO.getRemarks());
        menuCodes.setRecordsRequired(documentTypeCodesDTO.getRecordsRequired());
        log.info("About to save a menu code:{}", new ObjectMapper().writeValueAsString(menuCodes));
         var savedMenuCodes = dataService.saveMenuCodes(menuCodes);
         var documentTypeResDTO = modelMapper.map(menuCodes, DocumentTypeResDTO.class);
        return utilities.successResponse("saved menu codes",documentTypeResDTO);
    }

    public ResponseDTO updateMenuCode(DocumentTypeCodesDTO documentTypeCodesDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var menuCodes = dataService.findByMenuCodeId(id);
        log.info("menucodes :{}",documentTypeCodesDTO.toString());
        log.info("Fetched a menu code from the db:{}", objectMapper.writeValueAsString(menuCodes));
        menuCodes.setRecordsRequired(documentTypeCodesDTO.getRecordsRequired());
        log.info("Updated menu codes . About to save:{}", objectMapper.writeValueAsString(menuCodes));
        dataService.saveMenuCodes(menuCodes);
        return utilities.successResponse("updated menu codes  successfully",documentTypeCodesDTO);
    }

    public ResponseDTO getMenuCodes() throws JsonProcessingException {
        List<MenuCodes>menuCodesList = dataService.fetchAllMenuCodes();
        List<DocumentTypeResDTO>documentTypeResDTOS = menuCodesList.stream()
                .map(menuCodes -> {
                    return modelMapper.map(menuCodes,DocumentTypeResDTO.class);
                })
                .toList();
        log.info("Fetched  all  menu codes from the db :{}", new ObjectMapper().writeValueAsString(menuCodesList));
        return utilities.successResponse("Fetched all menu codes",documentTypeResDTOS);
    }


    /**
     * DOCUMENT TYPES
     * @param documentTypesDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO saveDocumentType(DocumentTypesDTO documentTypesDTO) throws JsonProcessingException {
        DocumentTypes documentTypes = new DocumentTypes();
        documentTypes.setName(documentTypesDTO.getName());
        documentTypes.setMenuCodes(dataService.findByMenuCodeId(documentTypesDTO.getMenuCodeId()));
        documentTypes.setSchool(dataService.findBySchoolId(documentTypesDTO.getSchoolId()));
        log.info("About to save a document type:{}", new ObjectMapper().writeValueAsString(documentTypes));
        dataService.saveDocumentTypes(documentTypes);
        return utilities.successResponse("saved a document type",null);
    }

    public ResponseDTO updateDocumentType(DocumentTypesDTO documentTypesDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        DocumentTypes documentTypes = dataService.findByDocumentTypeId(id);
        log.info("Fetched a document type  from the db:{}", objectMapper.writeValueAsString(documentTypes));
        documentTypes.setName(documentTypesDTO.getName());
        log.info("Updated document types . About to save:{}", objectMapper.writeValueAsString(documentTypes));
        dataService.saveDocumentTypes(documentTypes);
        return utilities.successResponse("updated document types  successfully",documentTypesDTO);
    }


    public ResponseDTO getDocumentTypes() throws JsonProcessingException {
        List<DocumentTypes>documentTypesList = dataService.fetchAllDocumentTypes();
        List<DocumentTypesDTO>documentTypesDTOList = documentTypesList.stream()
                .map(documentTypes -> {
                    DocumentTypesDTO documentTypesDTO = new DocumentTypesDTO();
                    documentTypesDTO.setName(documentTypes.getName());
                    documentTypesDTO.setSchoolId(documentTypes.getSchool().getSchoolId());
                    documentTypesDTO.setMenuCodeId(documentTypes.getMenuCodes().getMenuCodeId());
                    return documentTypesDTO;
                })
                .toList();
        log.info("Fetched  all document types from the db :{}", new ObjectMapper().writeValueAsString(documentTypesList));
        return utilities.successResponse("Fetched all document types",documentTypesDTOList);
    }

    public ResponseDTO getDocumentTypeByMenuCodeId(int id) throws JsonProcessingException {
        MenuCodes menuCodes = dataService.findByMenuCodeId(id);
        List<DocumentTypes>documentTypesList = dataService.findByMenuCodes(menuCodes);
        List<DocumentTypeResDTO>documentTypeResDTOS = documentTypesList.stream()
                .map(documentTypes -> {
                    return DocumentTypeResDTO.builder()
                            .menuCodeId(documentTypes.getMenuCodes().getMenuCodeId())
                            .name(documentTypes.getName())
                            .required(documentTypes.getMenuCodes().isRequired())
                            .remarks(documentTypes.getMenuCodes().getRemarks())
                            .recordsRequired(documentTypes.getDocumentTypeId())
                            .build();
                })
                .toList();
        log.info("fetched all subjects per level {}",new ObjectMapper().writeValueAsString(documentTypesList));
        return utilities.successResponse("fetched doc types by menucodeId",documentTypeResDTOS);

    }


    /**
     * IDENTITY TYPES
     * @param identityTypeDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO createIdentityType(IdentityTypeDTO identityTypeDTO) throws JsonProcessingException {
        var identityType = modelMapper.map(identityTypeDTO,IdentityType.class);
        log.info("About to save an identity type:{}", new ObjectMapper().writeValueAsString(identityType));
        dataService.saveIdentityType(identityType);
        return utilities.successResponse("saved an identity  type",null);
    }


    public ResponseDTO updateIdentityType(IdentityTypeDTO identityTypeDTO, int id) throws JsonProcessingException {
            var objectMapper = new ObjectMapper();
            IdentityType identityType = dataService.findByIdentityTypeId(id);
            log.info("About to fetch an identity type  from the db:{}", objectMapper.writeValueAsString(identityType));
            modelMapper.map(identityType,identityTypeDTO);
            log.info("Updated identity types . About to save:{}", objectMapper.writeValueAsString(identityType));
            dataService.saveIdentityType(identityType);
            return utilities.successResponse("updated document types  successfully",identityTypeDTO);
        }

    public ResponseDTO getIdentityTypes() throws JsonProcessingException {
        List<IdentityType>identityTypeList = dataService.FetchAllIdentityTypes();
        List<IdentityTypeDTO>identityTypeDTOList = identityTypeList.stream()
                .map(identityType -> {
                    return modelMapper.map(identityType,IdentityTypeDTO.class);
                })
                .toList();
        log.info("Fetched  all identity types from the db :{}", new ObjectMapper().writeValueAsString(identityTypeList));
        return utilities.successResponse("Fetched all identity types ",identityTypeDTOList);

    }


    /**
     * SUPPORTING DOCUMENTS
     * @param supportDocDTO the request dto
     * @return the response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO CreateSupportDocuments(String support, MultipartFile file) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        SupportDocDTO supportDocDTO= objectMapper.readValue(support, SupportDocDTO.class);
        String fileName = fileUpload.uploadImage(docPath,file);
        var documentType = dataService.findByDocumentTypeId(supportDocDTO.getDocumentTypeId());
        var schoolsEntity = dataService.findBySchoolId(supportDocDTO.getSchoolId());
        var menuCodes = dataService.findByMenuCodeId(supportDocDTO.getMenuCodeId());
        float percentage = Float.valueOf(file.getSize()/ menuCodes.getRecordsRequired()*100);
        SupportingDocuments supportingDocuments = new SupportingDocuments();
        documentType.setName(support);
        supportingDocuments.setDocumentTypes(documentType);
        supportingDocuments.setSchoolsEntity(schoolsEntity);
        supportingDocuments.setMenuCodes(menuCodes);
        supportingDocuments.setName(supportDocDTO.getName());
        supportingDocuments.setDescription(supportDocDTO.getDescription());
        dataService.saveSupportDocs(supportingDocuments);
        dataService.saveMenuCodes(menuCodes);
        return utilities.successResponse("created a school supporting document",supportingDocuments);

    }


    public ResponseDTO getAllBySupportId(int id) throws JsonProcessingException {
       var supportingDocuments= dataService.findBySupportDocId(id);
        log.info("Fetching a support document:{}", new ObjectMapper().writeValueAsString(supportingDocuments));
        SupportDocDTO supportDocDTO = modelMapper.map(supportingDocuments, SupportDocDTO.class);
        return utilities.successResponse("Successfully fetched a supporting doc",supportDocDTO);
    }


    public ResponseDTO getAllSupportDocs() throws JsonProcessingException {
        List<SupportingDocuments>supportingDocumentsList =dataService.FetchAllSupportingDocs();
        List<SupportDocDTO>supportDocDTOList = supportingDocumentsList.stream()
                .map(supportingDocuments -> {
                    return modelMapper.map(supportingDocuments,SupportDocDTO.class);
                })
                .toList();
        log.info("Fetched  all supporting documents  from the db :{}", new ObjectMapper().writeValueAsString(supportingDocumentsList));
        return utilities.successResponse("Fetched all supporting documents ",supportDocDTOList);

    }

    public ResponseDTO deleteSupportDocs(int id) {
        SupportingDocuments supportingDocuments =dataService.findBySupportDocId(id);
        supportingDocuments.setStatus(Status.DELETED);
        dataService.saveSupportDocs(supportingDocuments);
        return utilities.successResponse("deleted supporting documents",null);
    }



    /**
     *
     * @param schoolDocumentData the param
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO createSchoolDocument(String schoolDocumentData, List<MultipartFile> fileDocs) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        DocumentsDTO documentsDTO= objectMapper.readValue(schoolDocumentData, DocumentsDTO.class);
        var documentType = dataService.findByDocumentTypeId(documentsDTO.getDocumentTypeId());
        var schoolsEntity = dataService.findBySchoolId(documentsDTO.getSchoolId());
        var supportingDocuments = dataService.findBySupportDocId(documentsDTO.getSupportDocId());
        var menuCodes = dataService.findByMenuCodeId(documentsDTO.getMenuCodeId());
        SchoolMenuCodeStatuses schoolMenuCodeStatuses=dataService.findBySchoolMenuCodeStatusId(documentsDTO.getSchoolMenuCodeStatusId());
        if (dataService.findBySchoolMenuCodeStatusId(documentsDTO.getSchoolMenuCodeStatusId()) == null){
             var menucodes=dataService.findByMenuCodeId(documentsDTO.getMenuCodeId());
            if(menuCodes.getRecordsRequired()<= fileDocs.size()){
                float percentage = Float.valueOf(fileDocs.size()/ menuCodes.getRecordsRequired()*100);
                schoolMenuCodeStatuses.setStatus(Status.PENDING);
                schoolMenuCodeStatuses.setCompletionPercentage(percentage);
                schoolMenuCodeStatuses.setSchoolsEntity(schoolsEntity);
                schoolMenuCodeStatuses.setRemainingDocs(menuCodes.getRecordsRequired()-fileDocs.size());
                dataService.saveSchoolMenuCodeStatus(schoolMenuCodeStatuses);
                for (MultipartFile multipartFile:fileDocs){
                    String fileName = fileUpload.uploadImage(docPath,multipartFile);
                    SchoolDocuments schoolDocuments = new SchoolDocuments();
                    schoolDocuments.setDocumentTypes(documentType);
                    schoolDocuments.setSchoolsEntity(schoolsEntity);
                    schoolDocuments.setSupportingDocuments(supportingDocuments);
                    schoolDocuments.setMenuCodes(menuCodes);
                    schoolDocuments.setDocName(multipartFile.getName());
                    schoolDocuments.setDocUrl(fileName);
                    schoolDocuments.setDocSize(String.valueOf(multipartFile.getSize()));
                    schoolDocuments.setDocType(multipartFile.getContentType());
                    schoolDocuments.setDocKey(UUID.randomUUID().toString());
                    dataService.saveSchoolDocument(schoolDocuments);
                }
            } else if (menuCodes.getRecordsRequired() == fileDocs.size()){
                float percentage = Float.valueOf(fileDocs.size()/ menuCodes.getRecordsRequired()*100);
                schoolMenuCodeStatuses.setStatus(Status.COMPLETED);
                schoolMenuCodeStatuses.setCompletionPercentage(percentage);
                schoolMenuCodeStatuses.setSchoolsEntity(schoolsEntity);
                dataService.saveSchoolMenuCodeStatus(schoolMenuCodeStatuses);
                for (MultipartFile multipartFile:fileDocs){
                    String fileName = fileUpload.uploadImage(docPath,multipartFile);
                    SchoolDocuments schoolDocuments = new SchoolDocuments();
                    schoolDocuments.setDocumentTypes(documentType);
                    schoolDocuments.setSchoolsEntity(schoolsEntity);
                    schoolDocuments.setSupportingDocuments(supportingDocuments);
                    schoolDocuments.setMenuCodes(menuCodes);
                    schoolDocuments.setDocName(multipartFile.getName());
                    schoolDocuments.setDocUrl(fileName);
                    schoolDocuments.setDocSize(String.valueOf(multipartFile.getSize()));
                    schoolDocuments.setDocType(multipartFile.getContentType());
                    schoolDocuments.setDocKey(UUID.randomUUID().toString());
                    dataService.saveSchoolDocument(schoolDocuments);
                }

            }


        }else if (dataService.findBySchoolMenuCodeStatusId(documentsDTO.getSchoolMenuCodeStatusId())!=null){
            schoolMenuCodeStatuses.getRemainingDocs();
            schoolMenuCodeStatuses.setRemainingDocs(menuCodes.getRecordsRequired()-fileDocs.size());
            dataService.saveSchoolMenuCodeStatus(schoolMenuCodeStatuses);
            for (MultipartFile multipartFile:fileDocs){
                String fileName = fileUpload.uploadImage(docPath,multipartFile);
                SchoolDocuments schoolDocuments = new SchoolDocuments();
                schoolDocuments.setDocumentTypes(documentType);
                schoolDocuments.setSchoolsEntity(schoolsEntity);
                schoolDocuments.setSupportingDocuments(supportingDocuments);
                schoolDocuments.setMenuCodes(menuCodes);
                schoolDocuments.setDocName(multipartFile.getName());
                schoolDocuments.setDocUrl(fileName);
                schoolDocuments.setDocSize(String.valueOf(multipartFile.getSize()));
                schoolDocuments.setDocType(multipartFile.getContentType());
                schoolDocuments.setDocKey(UUID.randomUUID().toString());
                dataService.saveSchoolDocument(schoolDocuments);
            }
        }



        return utilities.successResponse("created a school document",documentsDTO);
    }

    public ResponseDTO updateSchoolDocument(String schoolDocumentData, MultipartFile fileDocs,int id) throws JsonProcessingException {
        var schoolDocuments = dataService.findBySchoolDocId(id);
        DocumentsDTO documentsDTO =modelMapper.map(schoolDocuments,DocumentsDTO.class);
        String fileName = fileUpload.uploadImage(docPath,fileDocs);
        var documentType = dataService.findByDocumentTypeId(documentsDTO.getDocumentTypeId());
        var schoolsEntity = dataService.findBySchoolId(documentsDTO.getSchoolId());
        var supportingDocuments = dataService.findBySupportDocId(documentsDTO.getSupportDocId());
        var menuCodes = dataService.findByMenuCodeId(documentsDTO.getMenuCodeId());
        schoolDocuments.setDocumentTypes(documentType);
        schoolDocuments.setSchoolsEntity(schoolsEntity);
        schoolDocuments.setSupportingDocuments(supportingDocuments);
        schoolDocuments.setMenuCodes(menuCodes);
        schoolDocuments.setDocName(fileDocs.getName());
        schoolDocuments.setDocUrl(fileName);
        schoolDocuments.setDocSize(String.valueOf(fileDocs.getSize()));
        schoolDocuments.setDocType(fileDocs.getContentType());
        schoolDocuments.setDocKey(UUID.randomUUID().toString());
        dataService.saveSchoolDocument(schoolDocuments);
        return utilities.successResponse("updated a school document  successfully",documentsDTO);
    }

    public ResponseDTO deleteSchoolDocument(int id) {
       var schoolDocuments =dataService.findBySchoolDocId(id);
       schoolDocuments.setStatus(Status.DELETED);
       schoolDocuments.getDocumentTypes().setStatus(Status.DELETED);
       schoolDocuments.getSchoolsEntity().setStatus(Status.DELETED);
       schoolDocuments.getMenuCodes().setStatus(Status.DELETED);
       schoolDocuments.getSupportingDocuments().setStatus(Status.DELETED);
       dataService.saveSchoolDocument(schoolDocuments);
        return utilities.successResponse("deleted school documents",null);

    }

    public ResponseDTO createSchoolFinanceDocument(String schoolDocumentData, MultipartFile fileDocs) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        DocumentsDTO documentsDTO= objectMapper.readValue(schoolDocumentData, DocumentsDTO.class);
        String fileName = fileUpload.uploadImage(docPath,fileDocs);
        SchoolDocuments newDocument = new SchoolDocuments();
        var documentType = dataService.findByDocumentTypeId(documentsDTO.getDocumentTypeId());
        var schoolsEntity = dataService.findBySchoolId(documentsDTO.getSchoolId());
        var supportingDocuments = dataService.findBySupportDocId(documentsDTO.getSupportDocId());
        var menuCodes = dataService.findByMenuCodeId(documentsDTO.getMenuCodeId());
        newDocument.setDocumentTypes(documentType);
        newDocument.setSupportingDocuments(supportingDocuments);
        newDocument.setMenuCodes(menuCodes);
        newDocument.setSchoolsEntity(schoolsEntity);
//        newDocument.setDocName(fileDocs.getName());
        newDocument.setDocUrl(fileName);
//        newDocument.setDocSize(String.valueOf(fileDocs.getSize()));
//        newDocument.setDocType(fileDocs.getContentType());
        newDocument.setDocKey(UUID.randomUUID().toString());
        dataService.saveNewDocument(newDocument);
        return utilities.successResponse("created a school finance document",documentsDTO);
    }

    public ResponseDTO updateSchoolFinanceDocument(String schoolDocumentData, MultipartFile fileDocs, int id) {
        var schoolDocument = dataService.findBySchoolDocId(id);
        DocumentsDTO documentsDTO =modelMapper.map(schoolDocument,DocumentsDTO.class);
        String fileName = fileUpload.uploadImage(docPath,fileDocs);
        var documentType = dataService.findByDocumentTypeId(documentsDTO.getDocumentTypeId());
        var schoolsEntity = dataService.findBySchoolId(documentsDTO.getSchoolId());
        var supportingDocuments = dataService.findBySupportDocId(documentsDTO.getSupportDocId());
        var menuCodes = dataService.findByMenuCodeId(documentsDTO.getMenuCodeId());
        schoolDocument.setDocumentTypes(documentType);
        schoolDocument.setSchoolsEntity(schoolsEntity);
        schoolDocument.setSupportingDocuments(supportingDocuments);
        schoolDocument.setMenuCodes(menuCodes);
        schoolDocument.setDocName(fileDocs.getName());
        schoolDocument.setDocUrl(fileName);
        schoolDocument.setDocSize(String.valueOf(fileDocs.getSize()));
        schoolDocument.setDocType(fileDocs.getContentType());
        schoolDocument.setDocKey(UUID.randomUUID().toString());
        dataService.saveNewDocument(schoolDocument);
        return utilities.successResponse("updated a school finance document  successfully",documentsDTO);
    }


    public ResponseDTO deleteSchoolFinanceDocument(DocumentsDTO documentsDTO, int id) {
        var schoolDocuments =dataService.findBySchoolDocId(id);
        schoolDocuments.setStatus(Status.DELETED);
        schoolDocuments.getDocumentTypes().setStatus(Status.DELETED);
        schoolDocuments.getSchoolsEntity().setStatus(Status.DELETED);
        schoolDocuments.getMenuCodes().setStatus(Status.DELETED);
        schoolDocuments.getSupportingDocuments().setStatus(Status.DELETED);
        dataService.saveNewDocument(schoolDocuments);
        return utilities.successResponse("deleted school documents",null);

    }

    public ResponseDTO createDirectorDocument(String directors, MultipartFile identityDoc, MultipartFile pinCertificateDoc) throws JsonProcessingException {
    var objectMapper = new ObjectMapper();
    DirectorsDTO directorsDTO = objectMapper.readValue(directors, DirectorsDTO.class);
    String fileName = fileUpload.uploadImage(docPath,identityDoc);
    String file = fileUpload.uploadImage(docPath,pinCertificateDoc);
    var documentType = dataService.findByDocumentTypeId(directorsDTO.getDocumentTypeId());
    var schoolsEntity = dataService.findBySchoolId(directorsDTO.getSchoolId());
    var menuCodes = dataService.findByMenuCodeId(directorsDTO.getMenuCodeId());
    var identityType = dataService.findByIdentityTypeId(directorsDTO.getIdentityTypeId());
    DirectorsDocsEntity directorsEntity = modelMapper.map(directorsDTO, DirectorsDocsEntity.class);
    directorsEntity.setIdentityDoc(fileName);
    directorsEntity.setPinCertificateDoc(file);
    directorsEntity.setSchoolsEntity(schoolsEntity);
    directorsEntity.setMenuCodes(menuCodes);
    directorsEntity.setDocumentTypes(documentType);
    directorsEntity.setIdentityType(identityType);
    dataService.saveDirectorsDocument(directorsEntity);
    return utilities.successResponse("Created directors document",directorsDTO);

    }

    public ResponseDTO updateDirectorsDocument(int id, MultipartFile identityDoc, MultipartFile pinCertificateDoc,String directors) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        DirectorsDocsEntity directorsEntity = dataService.findByDirectorDocId(id);
        DirectorsDocsEntity directorsEntity1 = objectMapper.readValue(directors, DirectorsDocsEntity.class);
        var documentType = directorsEntity.getDocumentTypes();
        var schoolsEntity = directorsEntity.getSchoolsEntity();
        var menuCodes = directorsEntity.getMenuCodes();
        var identityType =directorsEntity.getIdentityType();
        directorsEntity1.setSchoolsEntity(schoolsEntity);
        directorsEntity1.setDocumentTypes(documentType);
        directorsEntity1.setMenuCodes(menuCodes);
        directorsEntity1.setIdentityType(identityType);
        directorsEntity1.setIdentityDoc(directorsEntity.getIdentityDoc());
        directorsEntity1.setPinCertificateDoc(directorsEntity.getPinCertificateDoc());
        if (identityDoc !=null){
            String oldFile = docPath + "/" + directorsEntity.getIdentityDoc();
            fileUpload.deleteLocalPicture(oldFile);
            String fileName = fileUpload.uploadImage(docPath,identityDoc);
            directorsEntity1.setIdentityDoc(fileName);
        } if(pinCertificateDoc !=null) {
            String oldFile = docPath + "/" + directorsEntity.getPinCertificateDoc();
            fileUpload.deleteLocalPicture(oldFile);
            String file = fileUpload.uploadImage(docPath, pinCertificateDoc);
            directorsEntity1.setIdentityDoc(file);}
        dataService.saveDirectorsDocument(directorsEntity1);
        return utilities.successResponse("updated directors docs",null);

    }

    public ResponseDTO deleteDirectorsDocuments(int id) {
        DirectorsDocsEntity directors = dataService.findByDirectorDocId(id);
       directors.getDocumentTypes().setStatus(Status.DELETED);
       directors.getSchoolsEntity().setStatus(Status.DELETED);
       directors.setStatus(Status.DELETED);
       dataService.saveDirectorsDocument(directors);
       return utilities.successResponse("deleted a director's document",null);

    }


    /**
     * DIRECTORS
     * @param directorsRequestDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO createDirector(DirectorsRequestDTO directorsRequestDTO) throws JsonProcessingException {
        DirectorsEntity directorsEntity = modelMapper.map(directorsRequestDTO, DirectorsEntity.class);
        log.info("About to save a director's info:{}", new ObjectMapper().writeValueAsString(directorsEntity));
         dataService.saveDirector(directorsEntity);
         var savedDirector = modelMapper.map(directorsEntity,DirectorsRequestDTO.class);
        return utilities.successResponse("added a director ",savedDirector);
    }

    public ResponseDTO getAllDirectors() throws JsonProcessingException {
        List<DirectorsEntity>directorsEntityList = dataService.FetchAllDirectors();
        List<DirectorsRequestDTO>directorsRequestDTOList = directorsEntityList.stream()
                .map(directors -> {
                    return modelMapper.map(directors,DirectorsRequestDTO.class);
                })
                .toList();
        log.info("Fetched  all directors from the db :{}", new ObjectMapper().writeValueAsString(directorsEntityList));
        return utilities.successResponse("Fetched all directors ",directorsRequestDTOList);

    }


    public ResponseDTO getDirector(int id) throws JsonProcessingException {
        DirectorsEntity directors = dataService.findByDirectorId(id);
        log.info("Fetching a  director:{}", new ObjectMapper().writeValueAsString(directors));
        DirectorsRequestDTO directorsRequestDTO = modelMapper.map(directors, DirectorsRequestDTO.class);
        return utilities.successResponse("Successfully fetched a supporting doc",directorsRequestDTO);

    }

    public ResponseDTO updateDirector(DirectorsRequestDTO directorsRequestDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        DirectorsEntity directors = dataService.findByDirectorId(id);
        log.info("Fetched a director from the db:{}", objectMapper.writeValueAsString(directors));
        modelMapper.map(directors,directorsRequestDTO);
        log.info("Updated director details . About to save:{}", objectMapper.writeValueAsString(directors));
        dataService.saveDirector(directors);
        var updatedDirectorDetails = modelMapper.map(directors,DirectorsRequestDTO.class);
        return utilities.successResponse("updated director details  successfully",updatedDirectorDetails);
    }


    public ResponseDTO deleteDirector(int id) {
        DirectorsEntity directors = dataService.findByDirectorId(id);
        directors.setStatus(Status.DELETED);
        dataService.saveDirector(directors);
        return utilities.successResponse("deleted a director",null);
    }

    public ResponseDTO submitSchoolForApproval(SubmitSchoolDTO submitSchoolDTO) {
        SchoolsEntity schoolsEntity = dataService.findBySchoolId(submitSchoolDTO.getSchoolId());
        if(schoolsEntity == null){
            return utilities.failedResponse(400, "School does not exist", null);
        }
        List<MenuCodes>menuCodesList =dataService.fetchAllMenuCodes();
        boolean allRequiredCompleted = menuCodesList.stream()
                .filter(MenuCodes ::isRequired)
                .allMatch(menuCodes -> {
                    SchoolMenuCodeStatuses schoolMenuCodeStatuses = dataService.findBySchoolEntityAndMenuCodes(schoolsEntity,menuCodes);
                    if ( schoolMenuCodeStatuses != null && schoolMenuCodeStatuses.getStatus().equals(Status.COMPLETED)){
                        return true;
                    } return false;

                });
        if(allRequiredCompleted){
            schoolsEntity.setStatus(Status.SUBMITTED);
            dataService.saveSchool(schoolsEntity);
            return utilities.successResponse("School has been approved",null);
        }else{
            schoolsEntity.setStatus(Status.PENDING);
            dataService.saveSchool(schoolsEntity);
            return utilities.failedResponse(408, "The school does not meet criteria", null);
        }

    }

    public ResponseDTO approveSchool(ApproveSchoolDTO approveSchoolDTO) {
        SchoolsEntity schoolsEntity = dataService.findBySchoolId(approveSchoolDTO.getSchoolId());
        if(schoolsEntity.getStatus().equals(Status.APPROVED.name())){
            return utilities.failedResponse(400, "School has been approved", null);
        }
        PartnerInfoEntity partnerInfo = dataService.findByUserEntity(dataService.findByEmail(AuthenticatedUser.username()).get());

        List<MenuCodes>menuCodesList =dataService.fetchAllMenuCodes();
        boolean allRequiredCompleted = menuCodesList.stream()
                .filter(MenuCodes ::isRequired)
                .allMatch(menuCodes -> {
                    SchoolMenuCodeStatuses schoolMenuCodeStatuses = dataService.findBySchoolEntityAndMenuCodes(schoolsEntity, menuCodes);
                    if (schoolMenuCodeStatuses != null && schoolMenuCodeStatuses.getStatus().equals(Status.COMPLETED)) {
                        return true;
                    }
                    return false;
                });
                    if (allRequiredCompleted) {
                        schoolsEntity.setStatus(Status.APPROVED);
                        SchoolsEntity savedschool =dataService.saveSchool(schoolsEntity);
                    }
                    menuCodesList.forEach(menuCode -> {
                        Optional<SchoolMenuCodeStatuses> statusOpt = Optional.ofNullable(dataService.findBySchoolEntityAndMenuCodes(schoolsEntity, menuCode));
                        statusOpt.ifPresent(status -> {
                            status.setStatus(Status.COMPLETED);
                            status.setApprovedBy(partnerInfo.getPartnerId());
                            status.setRemarkStatus(RemarksClarificationStatus.CLOSED);
                            status.setRemarks("Reviewed And Approved");
                            dataService.saveSchoolMenuCodeStatus(status);

                        });
                    });

                    PartnerApprovalEntity partnerApproval = PartnerApprovalEntity.builder()
                        .partnerInfo(partnerInfo)
                        .remarks("Approved this school")
                        .schoolsEntity(schoolsEntity)
                        .status(schoolsEntity.getStatus())
                        .build();
                    dataService.savePartnerApproval(partnerApproval);
                    emailService.sendEmail(schoolsEntity,"School approved");
                    List<SystemAdminEntity>systemAdminEntities = dataService.viewAll();
                    for(SystemAdminEntity systemAdmin: systemAdminEntities){
                        emailService.send(systemAdmin.getUserEntity(), "School "+schoolsEntity.getSchoolName()+" approved");
                    }
                    return null;

    }


    public ResponseDTO rejectSchool(ApproveSchoolDTO approveSchoolDTO) {
        SchoolsEntity schoolsEntity = dataService.findBySchoolId(approveSchoolDTO.getSchoolId());
        if (schoolsEntity.getStatus().equals(Status.APPROVED.name())) {
            return utilities.failedResponse(400, "School cannot be rejected since its already approved", null);
        }
        if (schoolsEntity.getStatus().equals(Status.REJECTED.name())) {
            return utilities.failedResponse(400, "School has been rejected", null);
        }

        PartnerInfoEntity partnerInfo = dataService.findByUserEntity(dataService.findByEmail(AuthenticatedUser.username()).get());

        List<MenuCodes> menuCodesList = dataService.fetchAllMenuCodes();
        SchoolsEntity finalSchoolsEntity = schoolsEntity;
        boolean allRequiredCompleted = menuCodesList.stream()
                .filter(MenuCodes::isRequired)
                .allMatch(menuCodes -> {
                    SchoolMenuCodeStatuses schoolMenuCodeStatuses = dataService.findBySchoolEntityAndMenuCodes(finalSchoolsEntity, menuCodes);
                    if (schoolMenuCodeStatuses != null && schoolMenuCodeStatuses.getStatus().equals(Status.COMPLETED)) {
                        return true;
                    }
                    return false;
                });
        if (schoolsEntity != null) {
            return utilities.failedResponse(00, "School already rejected", null);
        }
        if (allRequiredCompleted) {
            schoolsEntity = dataService.findBySchoolId(approveSchoolDTO.getSchoolId());
            schoolsEntity.setStatus(Status.REJECTED);
            dataService.saveSchool(schoolsEntity);
            SchoolsEntity finalSchoolsEntity1 = schoolsEntity;
            menuCodesList.forEach(menuCode -> {
                Optional<SchoolMenuCodeStatuses> statusOpt = Optional.ofNullable(dataService.findBySchoolEntityAndMenuCodes(finalSchoolsEntity1, menuCode));
                statusOpt.ifPresent(status -> {
                    status.setStatus(statusOpt.get().getStatus());
                    status.setApprovedBy(partnerInfo.getPartnerId());
                    status.setRemarkStatus(RemarksClarificationStatus.CLOSED);
                    status.setRemarks("School Rejected");
                    dataService.saveSchoolMenuCodeStatus(status);

                });
            });
            PartnerApprovalEntity partnerApproval = PartnerApprovalEntity.builder()
                    .partnerInfo(partnerInfo)
                    .remarks("Rejected this school")
                    .schoolsEntity(schoolsEntity)
                    .status(schoolsEntity.getStatus())
                    .build();
            dataService.savePartnerApproval(partnerApproval);
            emailService.sendEmail(schoolsEntity, "School rejected");
            List<SystemAdminEntity> systemAdminEntities = dataService.viewAll();
            for (SystemAdminEntity systemAdmin : systemAdminEntities) {
                emailService.send(systemAdmin.getUserEntity(), "School " + schoolsEntity.getSchoolName() + " Rejected");
            }

        }

return null;
    }

//
//    public ResponseDTO raiseClarification(ClarifyDTO clarifyDTO) {
//        SchoolsEntity schoolsEntity = dataService.findBySchoolId(clarifyDTO.getSchoolId());
//        PartnerInfoEntity partnerInfo = dataService.findByUserEntity(dataService.findByEmail(AuthenticatedUser.username()).get());
//        List<MenuCodes> menuCodesList = dataService.fetchAllMenuCodes();
//        boolean allRequiredCompleted = menuCodesList.stream()
//                .filter(MenuCodes::isRequired)
//                .allMatch(menuCodes -> {
//                    SchoolMenuCodeStatuses schoolMenuCodeStatuses = dataService.findBySchoolEntityAndMenuCodes(schoolsEntity, menuCodes);
//                    if (schoolMenuCodeStatuses != null && schoolMenuCodeStatuses.getStatus().equals(Status.COMPLETED)) {
//                        return true;
//                    }
//                    return false;
//                });
//
//        SchoolsEntity findSchool = null;
//        SchoolsEntity school = null;
//        findSchool = dataService.findBySchoolId(clarifyDTO.getSchoolId());
//        if (findSchool == null) {
//            return utilities.failedResponse(00,"School provided does not exists",null);
//        }
//        school= dataService.findBySchoolIdAndStatus(findSchool.getSchoolId(),Status.APPROVED);
//        if (school !=null){
//            return utilities.failedResponse(00,"School cannot be rejected since its already approved",null);
//        }
//        school= dataService.findBySchoolIdAndStatus(findSchool.getSchoolId(),Status.REJECTED);
//        if (school !=null){
//            return utilities.failedResponse(00,"School cannot be sent for clarification since its already rejected",null);
//        }
//        Optional<SchoolMenuCodeStatuses> schoolMenuCodeOpen = dataService.findBySchoolsEntitySchoolIdAndMenuCodesMenuCodeIdAndRemarksClarificationStatus(findSchool.getSchoolId(),clarifyDTO.getMenuCodeId(),RemarksClarificationStatus.OPEN.name())
//                ;
//        if (schoolMenuCodeOpen != null) {
//            return utilities.failedResponse(00,"Clarification on this menu code %s already raised",schoolMenuCodeOpen.get());
//        }
//        findSchool.setStatus(Status.CLARIFICATION);
////        findSchool.setClarificationRaisedBy(CommonFunctions.convertLongToInt(authenticatedUser.getId()));
//        SchoolsEntity save = dataService.saveSchool(findSchool);
//        // Update the specific menu code status
//        Optional<SchoolMenuCodeStatuses> statusOpt = dataService.findBySchoolsEntitySchoolIdAndMenuCodeId(findSchool.getSchoolId(),clarifyDTO.getMenuCodeId());
//        statusOpt.ifPresent(status -> {
//            status.setStatus(Status.CLARIFICATION);
//            status.setRemarkStatus(RemarksClarificationStatus.OPEN);
//            status.setClarificationRaisedBy(AuthenticatedUser.username());
//            status.setRemarks(clarifyDTO.getRemarks());
//            dataService.saveSchoolMenuCodeStatus(status);
//        });
//
//                PartnerApprovalEntity partnerApproval = PartnerApprovalEntity.builder()
//                        .partnerInfo(partnerInfo)
//                        .remarks(clarifyDTO.getRemarks())
//                        .schoolsEntity(findSchool)
//                        .status(save.getStatus())
//                        .createdBy(AuthenticatedUser.username())
//                        .build();
//                dataService.savePartnerApproval(partnerApproval);
//                List<AgentInfoEntity> agentInfoEntityList = dataService.fetchActiveAgents();
//                for (AgentInfoEntity agentInfo : agentInfoEntityList) {
//                    emailService.send(agentInfo.getUserEntity(), "School " + schoolsEntity.getSchoolName() + "school_clarification_remark_concern,\n");
//                }
//
//        return null;
//    }


    public ResponseDTO replyOnClarification(ReplyOnClarificationDTO replyOnClarificationDTO) {
        SchoolsEntity schoolsEntity = dataService.findBySchoolId(replyOnClarificationDTO.getSchoolId());
        PartnerInfoEntity partnerInfo = dataService.findByUserEntity(dataService.findByEmail(AuthenticatedUser.username()).get());
        SchoolsEntity findSchool = null;
        SchoolsEntity school = null;
        findSchool = dataService.findBySchoolId(replyOnClarificationDTO.getSchoolId());
        if (findSchool == null) {
            return utilities.failedResponse(00, "School provided does not exists", null);
        }

        school = dataService.findBySchoolIdAndStatus(findSchool.getSchoolId(),Status.APPROVED);
        if (school != null) {
            return utilities.failedResponse(00,"School cannot have its clarification raised since its already approved", null);
        }
        Optional<MenuCodes> menuCodeById = Optional.ofNullable(dataService.findByMenuCodeId(replyOnClarificationDTO.getMenuCodeId()));
        if (menuCodeById == null) {
            return utilities.failedResponse(00, "Menu code provided not exist", null);
        }
        List<SchoolMenuCodeStatuses> allStatuses = dataService.findAllBySchoolsEntitySchoolId(school.getSchoolId());
        boolean allOpenedOrClosed = allStatuses.stream().allMatch(s ->
                RemarksClarificationStatus.OPEN.equals(s.getRemarkStatus())
        );

        findSchool.setStatus(allOpenedOrClosed ? Status.CLARIFICATION : Status.SUBMITTED);
//        findSchool.setClarificationRepliedBy(CommonFunctions.convertLongToInt(authenticatedUser.getId()));
        dataService.saveSchool(findSchool);

        // Update the specific menu code status
        List<MenuCodes> menuCodesList = dataService.fetchAllMenuCodes();
        for(MenuCodes menuCodes: menuCodesList){
            SchoolMenuCodeStatuses statusOpt = dataService.findBySchoolEntityAndMenuCodes(schoolsEntity, menuCodes);
            statusOpt.setStatus(allOpenedOrClosed ? Status.CLARIFICATION : Status.COMPLETED);
            statusOpt.setRemarkStatus(RemarksClarificationStatus.REVIEW);
            statusOpt.setRemarks(replyOnClarificationDTO.getRemarks());
//            statusOpt.setClarificationRepliedBy(AuthenticatedUser.username().equals());
            dataService.saveSchoolMenuCodeStatus(statusOpt);
        }


        PartnerApprovalEntity approvalStatusBuild = PartnerApprovalEntity.builder()
                .partnerInfo(partnerInfo)
                .schoolsEntity(findSchool)
                .remarks(replyOnClarificationDTO.getRemarks())
                .status(school.getStatus())
//                .createdBy(CommonFunctions.convertLongToInt(authenticatedUser.getId()))
                .build();
        dataService.savePartnerApproval(approvalStatusBuild);
        Optional<SchoolMenuCodeStatuses> finalStatusesBySchoolId = dataService. findBySchoolsEntitySchoolIdAndMenuCodeId(replyOnClarificationDTO.getSchoolId(),replyOnClarificationDTO.getMenuCodeId());
        boolean finalCheck = finalStatusesBySchoolId.stream().allMatch(s ->
                RemarksClarificationStatus.REVIEW.equals(s.getRemarkStatus()) ||
                RemarksClarificationStatus.CLOSED.equals(s.getRemarkStatus())
        );
        if (finalCheck) {
            findSchool.setStatus(Status.SUBMITTED);
        } else {
            findSchool.setStatus(Status.CLARIFICATION);
        }
        dataService.saveSchool(findSchool);
        List<PartnerInfoEntity> partnerInfoEntityList = dataService.fetchActivePartners();
        for (PartnerInfoEntity partnerInfo1 : partnerInfoEntityList) {
            emailService.send(partnerInfo1.getUserEntity(), "School " + schoolsEntity.getSchoolName() + Status.school_clarification_remark_concern);
        }
        return null;






    }

    public ResponseDTO closeRepliedClarification(ClarifyDTO clarifyDTO) {
        SchoolsEntity schoolsEntity = dataService.findBySchoolId(clarifyDTO.getSchoolId());
        PartnerInfoEntity partnerInfo = dataService.findByUserEntity(dataService.findByEmail(AuthenticatedUser.username()).get());
        SchoolsEntity findSchool = null;
        SchoolsEntity school = null;
        findSchool = dataService.findBySchoolId(clarifyDTO.getSchoolId());
        if (findSchool == null) {
            return utilities.failedResponse(00, "School provided does not exists", null);
        }

        school = dataService.findBySchoolIdAndStatus(findSchool.getSchoolId(),Status.APPROVED);
        if (school != null) {
            return utilities.failedResponse(00, "School cannot be sent for a clarification  since its already approved", null);
        }
        Optional<MenuCodes> menuCodeById = Optional.ofNullable(dataService.findByMenuCodeId(clarifyDTO.getMenuCodeId()));
        if (menuCodeById == null) {
            return utilities.failedResponse(00, "Menu code provided not exist", null);
        }
        List<SchoolMenuCodeStatuses> allStatuses =dataService.findAllBySchoolsEntitySchoolId(findSchool.getSchoolId());
        boolean allOpenedOrClosed = allStatuses.stream().allMatch(s ->
                        RemarksClarificationStatus.OPEN.equals(s.getRemarkStatus())
                //||
                //RemarksClarificationStatus.CLOSED.name().equals(s.getRemarkStatus())
        );
        if (allOpenedOrClosed) {
            return utilities.failedResponse(00,"The status clarification for %s must be either on review or closed",menuCodeById.get().getName());
        }

        findSchool.setStatus(allOpenedOrClosed ? Status.CLARIFICATION: Status.APPROVED);
//        findSchool.setClarificationClosedBy(AuthenticatedUser.username());
        SchoolsEntity save = dataService.saveSchool(findSchool);

        // Update the specific menu code status
        Optional<SchoolMenuCodeStatuses> statusOpt =(dataService.findBySchoolsEntitySchoolIdAndMenuCodeId(clarifyDTO.getSchoolId(), clarifyDTO.getMenuCodeId()));
        statusOpt.ifPresent(status -> {
            status.setStatus(allOpenedOrClosed ? Status.CLARIFICATION: Status.COMPLETED);
            status.setRemarkStatus(RemarksClarificationStatus.CLOSED);
            status.setRemarks(clarifyDTO.getRemarks());
//            status.setClarificationRepliedBy();
            dataService.saveSchoolMenuCodeStatus(status);
        });
        PartnerApprovalEntity approvalStatusBuild = PartnerApprovalEntity.builder()
                .partnerInfo(partnerInfo)
                .schoolsEntity(findSchool)
                .remarks(clarifyDTO.getRemarks())
                .status(save.getStatus())
                .createdBy(AuthenticatedUser.username())
                .build();
        dataService.savePartnerApproval(approvalStatusBuild);
        List<SchoolMenuCodeStatuses> finalStatusesBySchoolId = dataService.findAllBySchoolsEntitySchoolId(findSchool.getSchoolId());
        boolean finalCheck = finalStatusesBySchoolId.stream().allMatch(s ->
                RemarksClarificationStatus.CLOSED.equals(s.getRemarkStatus()) ||
                        RemarksClarificationStatus.CLOSED.equals(s.getRemarkStatus())
        );
        if (finalCheck) {
            findSchool.setStatus(Status.APPROVED);
        } else {
            findSchool.setStatus(Status.CLARIFICATION);
        }
        dataService.saveSchool(findSchool);
        List<PartnerInfoEntity> partnerInfoEntityList = dataService.fetchActivePartners();
        for (PartnerInfoEntity partnerInfo1 : partnerInfoEntityList) {
            emailService.send(partnerInfo1.getUserEntity(), "School " + schoolsEntity.getSchoolName() + Status.school_clarification_closed);
        }
        List<AgentInfoEntity> agentInfoEntityList = dataService.fetchActiveAgents();
        for (AgentInfoEntity agentInfo : agentInfoEntityList) {
            emailService.send(agentInfo.getUserEntity(), "School " + schoolsEntity.getSchoolName() + Status.school_clarification_closed);
        }


return null;
    }




}