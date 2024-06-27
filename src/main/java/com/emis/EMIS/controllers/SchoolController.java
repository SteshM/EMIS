package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolService;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Nullable;
import jakarta.websocket.server.PathParam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/agent")
public class SchoolController {
    private final SchoolService schoolService;

    /**
     * SCHOOL
     * A method to enrol a school
     * @return agent Service
     */

    @PostMapping("/school")
    public ResponseDTO enrolSchool(@RequestPart String schoolFormData , @Nullable @RequestPart MultipartFile logo) throws JsonProcessingException {
        log.info("uploading a school form data :: {}",schoolFormData);
        if (logo == null){
            return schoolService.createBasicInfoWithoutFile(schoolFormData);
        }
        return schoolService.createBasicInfoWithFile(schoolFormData,logo);
    }
    @GetMapping("/schools")
    public ResponseDTO fetchSchools() throws JsonProcessingException {
        log.info("About to fetch school details");
        return schoolService.viewSchools();
    }
    @GetMapping("/school/{id}")
    public ResponseDTO getSchool(@PathVariable int id) throws JsonProcessingException {
        return schoolService.getSchool(id);
    }
    @PutMapping("/school/{id}")
    public ResponseDTO updateSchool(@PathVariable int id, @RequestBody SchoolDTO schoolDTO) throws JsonProcessingException {
        return schoolService.updateSchool(id,schoolDTO);
    }
    @DeleteMapping("/school/{id}")
    public ResponseDTO deleteSchool(@PathVariable int id){
        return schoolService.deleteSchool(id);
    }


    /**
     *
     * @param schoolType param
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/school-type")
    public ResponseDTO addSchoolType(@PathParam("schoolType") String schoolType ) throws JsonProcessingException {
        return schoolService.addSchoolType(schoolType);
    }
    @GetMapping("/school-type")
    public ResponseDTO getAllSchoolTypes() throws JsonProcessingException {
        return schoolService.getAllSchoolTypes();
    }
    @PutMapping("/school-type/{id}")
    public ResponseDTO updateSchoolType(@PathVariable int id,@RequestBody SchoolTypeDTO schoolTypeDTO) throws JsonProcessingException {
        return schoolService.updateSchoolType(id,schoolTypeDTO);
    }


    /**
     * SCHOOL GENDER
     * @param schoolGenderDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/school-gender")
    public ResponseDTO addSchoolGender(@RequestBody SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        return schoolService.addSchoolGender(schoolGenderDTO);
    }
    @GetMapping("/school-gender")
    public ResponseDTO getAllSchoolGenders() throws JsonProcessingException {
        return schoolService.getAllSchoolGenders();
    }
    @PutMapping("/school-gender/{id}")
    public ResponseDTO updateSchoolGender(@PathVariable int id,@RequestBody SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        return schoolService.updateSchoolGender(id,schoolGenderDTO);
    }

    /**
     * CURRICULUM
     * @param curriculumDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/curriculum")
    public ResponseDTO addCurriculum(@RequestBody CurriculumDTO curriculumDTO) throws JsonProcessingException {
        return schoolService.addCurriculum(curriculumDTO);
    }
    @GetMapping("/curriculums")
    public ResponseDTO getAllCurriculums() throws JsonProcessingException {
        return schoolService.getCurriculums();
    }
    @PutMapping("/curriculum/{id}")
    public ResponseDTO updateCurriculum(@PathVariable int id,@RequestBody CurriculumDTO curriculumDTO) throws JsonProcessingException {
        return schoolService.updateCurriculum(id,curriculumDTO);
    }


    /**
     * CATEGORY
     * @param categoryDTO the request dto
     * @return the response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/category")
    public ResponseDTO addCategory(@RequestBody CategoryDTO categoryDTO) throws JsonProcessingException {
        return  schoolService.addCategory(categoryDTO);
    }
    @PutMapping("/category/{id}")
    public ResponseDTO updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateCategory(categoryDTO,id);}

    @GetMapping("/category")
    public ResponseDTO getCategories() throws JsonProcessingException {
        return schoolService.getCategories();
    }

    /**
     * DESIGNATION
     * @param designationDTO request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/designation")
    public ResponseDTO addDesignation(@RequestBody DesignationDTO designationDTO) throws JsonProcessingException {
        return schoolService.addDesignation(designationDTO);
    }
    @PutMapping("/designation/{id}")
    public ResponseDTO updateDesignation(@RequestBody DesignationDTO designationDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateDesignation(designationDTO,id);}

    @GetMapping("/designation")
    public ResponseDTO getDesignations() throws JsonProcessingException {
        return schoolService.getDesignations();
    }

    /**
     * DIOCESE
     * @param dioceseDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/diocese")
    public ResponseDTO addDiocese(@RequestBody DioceseDTO dioceseDTO) throws JsonProcessingException {
        return schoolService.addDiocese(dioceseDTO);
    }
    @PutMapping("/diocese/{id}")
    public ResponseDTO updateDiocese(@RequestBody DioceseDTO dioceseDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateDiocese(dioceseDTO,id);}

    @GetMapping("/diocese")
    public ResponseDTO getDioceses() throws JsonProcessingException {
        return schoolService.getDioceses();
    }

    /**
     * SCHOOL CONTACTS
     * @param schoolContactsDTO  the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/school-contact/add")
    public ResponseDTO addSchoolContacts(@RequestBody SchoolContactsDTO schoolContactsDTO) throws JsonProcessingException {
        return schoolService.createSchoolContact(schoolContactsDTO);
    }
    @PutMapping("/school-contact/{id}")
    public ResponseDTO updateSchoolContact(@RequestBody SchoolContactsDTO schoolContactsDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateSchoolContacts(schoolContactsDTO,id);
    }

    @GetMapping("/school-contact/{id}")
    public ResponseDTO getSchoolContact(@PathVariable int id) throws JsonProcessingException {
    return schoolService.getSchoolContact(id);
    }

    @GetMapping("/school-contacts")
    public ResponseDTO viewSchoolContacts() throws JsonProcessingException {
        return schoolService.viewSchoolContacts();
    }
//
//    @GetMapping("/get-all/for-school")
//    public ResponseDTO viewSchoolContactsForSchool(){
//        return schoolService.getSchoolContactsForSchool();
//    }
//
    @DeleteMapping("/delete-school-contacts/{id}")
    public ResponseDTO deleteSchoolContacts(@PathVariable int id){
        return schoolService.deleteSchoolContacts(id);
    }


    /**
     * MENU CODES
     * @param documentTypeCodesDTO request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/save/menu-code")
    public ResponseDTO saveMenuCode(@RequestBody DocumentTypeCodesDTO documentTypeCodesDTO) throws JsonProcessingException {
        return schoolService.saveMenuCode(documentTypeCodesDTO);
    }
    @PutMapping("/menu-code/update/{id}")
    public ResponseDTO updateMenuCode(@RequestBody DocumentTypeCodesDTO documentTypeCodesDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateMenuCode(documentTypeCodesDTO,id);}

    @GetMapping("/menu-codes/all")
    public ResponseDTO getAllMenuCodes() throws JsonProcessingException {
        return schoolService.getMenuCodes();
    }

    /**
     * DOCUMENT TYPES
     * @param documentTypesDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/save/document-type")
    public ResponseDTO saveDocumentType(@RequestBody DocumentTypesDTO documentTypesDTO) throws JsonProcessingException {
        return schoolService.saveDocumentType(documentTypesDTO);
    }
    @PutMapping("/document-type/update/{id}")
    public ResponseDTO updateDocumentType(@RequestBody DocumentTypesDTO documentTypesDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateDocumentType(documentTypesDTO,id);}

    @GetMapping("/document-types/all")
    public ResponseDTO getAll() throws JsonProcessingException {
        return schoolService.getDocumentTypes();
    }
////get by menu code id
//    @GetMapping("/document-type/menu-code/{id}")
//    public ResponseDTO getByMenuCodeId(@PathVariable int id){
//        return schoolService.getDocumentTypeByMenuCodeId(id);
//    }

    /**
     *
     * @param identityTypeDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/identity-type")
    public ResponseDTO createIdentityType(@RequestBody IdentityTypeDTO identityTypeDTO) throws JsonProcessingException {
        return schoolService.createIdentityType(identityTypeDTO);
    }
    @PutMapping("/identity-type/{id}")
    public ResponseDTO updateIdentityType(@RequestBody IdentityTypeDTO identityTypeDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateIdentityType(identityTypeDTO,id);}

    @GetMapping("/identity-types/all")
    public ResponseDTO getAllTypes() throws JsonProcessingException {
        return schoolService.getIdentityTypes();
    }
//@PostMapping("/statuses/get-all")
//    public ResponseDTO getAllStatuses(@RequestBody MenuCodeStatusesDTO menuCodeStatusesDTO){
//        return schoolService.getAllMenuCodeStatuses();
//}


    /**
     * SUPPORTING DOCUMENTS
     * @param supportDocDTO request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */


    @PostMapping("/support-docs")
    public ResponseDTO addSupportDocuments(@RequestBody SupportDocDTO supportDocDTO) throws JsonProcessingException {
        return schoolService.CreateSupportDocuments(supportDocDTO);
    }

    @GetMapping("/support-docs/{id}")
    public ResponseDTO getALlBySupportId(@PathVariable int id) throws JsonProcessingException {
        return schoolService.getAllBySupportId(id);
    }

    @GetMapping("/support-docs")
    public ResponseDTO getAllSupportDocs() throws JsonProcessingException {
        return schoolService.getAllSupportDocs();
    }

    @DeleteMapping("/support-docs/{id}")
    public ResponseDTO deleteSupportDocs(@PathVariable int id){
        return schoolService.deleteSupportDocs(id);
    }

    /**
     * SCHOOL DOCUMENTS
     * @param schoolDocumentData school document data
     * @param
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/school-doc")
    public ResponseDTO createSchoolDocument(@RequestPart ("schoolDocumentData")String schoolDocumentData, @RequestPart ("fileDocs")MultipartFile fileDocs ) throws JsonProcessingException {
        return schoolService.createSchoolDocument(schoolDocumentData,fileDocs);
    }
    @PutMapping("/school-doc/{id}")
    public ResponseDTO updateSchoolDocument(@RequestPart("schoolDocumentData") String schoolDocumentData, @RequestPart("fileDocs") MultipartFile fileDocs,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateSchoolDocument(schoolDocumentData,fileDocs,id);
    }
    @DeleteMapping("/school-doc/{id}")
    public ResponseDTO deleteSchoolDocument(@PathVariable int id){
        return schoolService.deleteSchoolDocument(id);
    }

    @PostMapping("/school-finance-doc")
    public ResponseDTO createSchoolFinanceDocument(@RequestPart("schoolDocumentData") String schoolDocumentData, @RequestPart("fileDocs") MultipartFile fileDocs ) throws JsonProcessingException {
        return schoolService.createSchoolFinanceDocument(schoolDocumentData,fileDocs);
    }
    @PutMapping("/school-finance-doc/{id}")
    public ResponseDTO updateSchoolFinanceDocument(@RequestPart String schoolDocumentData, @RequestPart MultipartFile file,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateSchoolFinanceDocument(schoolDocumentData,file,id);
    }
    @DeleteMapping("/school-finance-doc/{id}")
    public ResponseDTO deleteSchoolFinanceDocument(@RequestBody DocumentsDTO documentsDTO,@PathVariable int id){
        return schoolService.deleteSchoolFinanceDocument(documentsDTO,id);
    }





}
