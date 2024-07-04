package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.FileUpload;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {
 private final ModelMapper modelMapper;
 private final DataService dataService;
 private final Utilities utilities;
 private final FileUpload fileUpload;


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
        List<SchoolDTO>schoolDTOList = schoolsEntities.stream()
                .map(schools -> {
                    return modelMapper.map(schools, SchoolDTO.class);
                })
                .toList();
        log.info("Fetched  all school Details:{}", new ObjectMapper().writeValueAsString(schoolsEntities));
        return utilities.successResponse("Successfully fetched schools",schoolDTOList);

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
        dataService.saveSchool(school);
        return utilities.successResponse("Successfully updated school details",schoolDTO);
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

    public ResponseDTO addSchoolType(String schoolTypeName) throws JsonProcessingException {
       SchoolType schoolType = new SchoolType();
       schoolType.setName(schoolTypeName);
        log.info("About to save a schoolType basic info:{}", new ObjectMapper().writeValueAsString(schoolType));
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
        List<SchoolGenderDTO>schoolGenderDTOList = schoolGenderList.stream()
                .map(schoolGender -> {
                    return modelMapper.map(schoolGender,SchoolGenderDTO.class);
                })
                .toList();
        log.info("Fetched  all  school gender Details:{}", new ObjectMapper().writeValueAsString(schoolGenderList));
        return utilities.successResponse("fetched all school genders",schoolGenderDTOList);
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
        dataService.saveCurriculum(curriculum);
        //not saving
        return utilities.successResponse("added a curriculum",curriculumDTO);
    }

    public ResponseDTO getCurriculums() throws JsonProcessingException {
        List<CurriculumEntity>curriculumEntityList = dataService.fetchCurriculums();
        log.info("About to fetch all curriculums from the db::{}",curriculumEntityList);
        List<CurriculumDTO>curriculumDTOList = curriculumEntityList.stream()
                .map(curriculum -> {
                    return modelMapper.map(curriculum,CurriculumDTO.class);
                })
                .toList();
        log.info("Fetched  all  curriculum Details:{}", new ObjectMapper().writeValueAsString(curriculumEntityList));
        return utilities.successResponse("fetched all curriculums",curriculumDTOList);
    }

    public ResponseDTO updateCurriculum(int id, CurriculumDTO curriculumDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        CurriculumEntity curriculum = dataService.findByCurriculumId(id);
        log.info("Fetched a curriculum from the db:{}", objectMapper.writeValueAsString(curriculum));
        modelMapper.map(curriculum,curriculumDTO);
        log.info("Updated curriculum Details. About to save:{}", objectMapper.writeValueAsString(curriculum));
        dataService.saveCurriculum(curriculum);
        return utilities.successResponse("updated school type successfully",curriculumDTO);
    }


    /**
     * CATEGORY
     * @param categoryDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */


    public ResponseDTO addCategory(CategoryDTO categoryDTO) throws JsonProcessingException {
        var categoriesEntity = modelMapper.map(categoryDTO, CategoriesEntity.class);
        log.info("About to save a category:{}", new ObjectMapper().writeValueAsString(categoriesEntity));
        dataService.saveCategories(categoriesEntity);
        return utilities.successResponse("Added a category",null);

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
        List<CategoryDTO>categoryDTOList =categoriesEntities.stream()
                .map(categoriesEntity -> {
                    return modelMapper.map(categoriesEntity, CategoryDTO.class);
                })
                .toList();

        log.info("Fetched  all  categories :{}", new ObjectMapper().writeValueAsString(categoriesEntities));
return utilities.successResponse("fetched all categories",categoryDTOList);
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
        dataService.saveDesignation(designationEntity);
        return utilities.successResponse("Added designation",null);

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
        List<DesignationDTO>designationDTOList = designationEntityList.stream()
                .map(designationEntity -> {
                    return modelMapper.map(designationEntity, DesignationDTO.class);
                })
                .toList();
        log.info("Fetched  all  designations :{}", new ObjectMapper().writeValueAsString(designationEntityList));

return utilities.successResponse("Fetched all designations",designationDTOList);
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
        List<SchoolContactsDTO>schoolContactsDTOList = schoolContactsList.stream()
                .map(schoolContacts -> {
                    return modelMapper.map(schoolContacts,SchoolContactsDTO.class);
                })
                .toList();
        log.info("Fetched  all  school contacts :{}", new ObjectMapper().writeValueAsString(schoolContactsList));
        return utilities.successResponse("Fetched all school contacts",schoolContactsDTOList);
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
      var menuCodes = modelMapper.map(documentTypeCodesDTO,MenuCodes.class);
        log.info("About to save a menu code:{}", new ObjectMapper().writeValueAsString(menuCodes));
        dataService.saveMenuCodes(menuCodes);
        return utilities.successResponse("saved menu codes",null);
    }

    public ResponseDTO updateMenuCode(DocumentTypeCodesDTO documentTypeCodesDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var menuCodes = dataService.findByMenuCodeId(id);
        log.info("Fetched a menu code from the db:{}", objectMapper.writeValueAsString(menuCodes));
        modelMapper.map(menuCodes,documentTypeCodesDTO);
        log.info("Updated menu codes . About to save:{}", objectMapper.writeValueAsString(menuCodes));
        dataService.saveMenuCodes(menuCodes);
        return utilities.successResponse("updated menu codes  successfully",documentTypeCodesDTO);
    }

    public ResponseDTO getMenuCodes() throws JsonProcessingException {
        List<MenuCodes>menuCodesList = dataService.fetchAllMenuCodes();
        List<DocumentTypeCodesDTO>documentTypeCodesDTOS = menuCodesList.stream()
                .map(menuCodes -> {
                    return modelMapper.map(menuCodes,DocumentTypeCodesDTO.class);
                })
                .toList();
        log.info("Fetched  all  menu codes from the db :{}", new ObjectMapper().writeValueAsString(menuCodesList));
        return utilities.successResponse("Fetched all menu codes",documentTypeCodesDTOS);
    }


    /**
     * DOCUMENT TYPES
     * @param documentTypesDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO saveDocumentType(DocumentTypesDTO documentTypesDTO) throws JsonProcessingException {
        var documentTypes = modelMapper.map(documentTypesDTO,DocumentTypes.class);
        log.info("About to save a document type:{}", new ObjectMapper().writeValueAsString(documentTypes));
        dataService.saveDocumentTypes(documentTypes);
        return utilities.successResponse("saved a document type",null);
    }

    public ResponseDTO updateDocumentType(DocumentTypesDTO documentTypesDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        DocumentTypes documentTypes = dataService.findByDocumentTypeId(id);
        log.info("Fetched a document type  from the db:{}", objectMapper.writeValueAsString(documentTypes));
        modelMapper.map(documentTypes,documentTypesDTO);
        log.info("Updated document types . About to save:{}", objectMapper.writeValueAsString(documentTypes));
        dataService.saveDocumentTypes(documentTypes);
        return utilities.successResponse("updated document types  successfully",documentTypesDTO);
    }


    public ResponseDTO getDocumentTypes() throws JsonProcessingException {
        List<DocumentTypes>documentTypesList = dataService.fetchAllDocumentTypes();
        List<DocumentTypesDTO>documentTypesDTOList = documentTypesList.stream()
                .map(documentTypes -> {
                    return modelMapper.map(documentTypes,DocumentTypesDTO.class);
                })
                .toList();
        log.info("Fetched  all document types from the db :{}", new ObjectMapper().writeValueAsString(documentTypesList));
        return utilities.successResponse("Fetched all document types",documentTypesDTOList);
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

    public ResponseDTO CreateSupportDocuments(SupportDocDTO supportDocDTO) throws JsonProcessingException {
        SupportingDocuments supportingDocuments = modelMapper.map(supportDocDTO,SupportingDocuments.class);
        log.info("About to save supporting documents:{}", new ObjectMapper().writeValueAsString(supportingDocuments));
        dataService.saveSupportDocs(supportingDocuments);
        return utilities.successResponse("saved  supporting documents ",null);
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

    public ResponseDTO createSchoolDocument(String schoolDocumentData, MultipartFile fileDocs) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        DocumentsDTO documentsDTO= objectMapper.readValue(schoolDocumentData, DocumentsDTO.class);
        String fileName = fileUpload.uploadImage(docPath,fileDocs);
         var documentType = dataService.findByDocumentTypeId(documentsDTO.getDocumentTypeId());
         var schoolsEntity = dataService.findBySchoolId(documentsDTO.getSchoolId());
         var supportingDocuments = dataService.findBySupportDocId(documentsDTO.getSupportDocId());
         var menuCodes = dataService.findByMenuCodeId(documentsDTO.getMenuCodeId());
        SchoolDocuments schoolDocuments = new SchoolDocuments();
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
        newDocument.setDocName(fileDocs.getName());
        newDocument.setDocUrl(fileName);
        newDocument.setDocSize(String.valueOf(fileDocs.getSize()));
        newDocument.setDocType(fileDocs.getContentType());
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
        //add else if to check if there are any other checks, eg if the school has been soft deleted etc

        //check if has menu codes or something like that
        boolean hasMenuCodes = false;//ths is not constant
        //code for checking menu codes

        if(hasMenuCodes){
            schoolsEntity.setStatus(Status.APPROVED);
            //make other changes for school entity here and other saves
            dataService.saveSchool(schoolsEntity);
            return utilities.successResponse("School has been approved", schoolsEntity);
        }else{
            schoolsEntity.setStatus(Status.REJECTED);
            dataService.saveSchool(schoolsEntity);
            return utilities.failedResponse(408, "The school does not meet criteria", null);
        }
    }
}



