package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolService;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/agent")
@CrossOrigin("*")
public class SchoolController {
    private final SchoolService schoolService;

    /**
     * SCHOOL
     * A method to enrol a school
     * @return agent Service
     */

    @PostMapping("/school")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_SCHOOL')")
    public ResponseDTO enrolSchool(@RequestPart String schoolFormData , @Nullable @RequestPart MultipartFile logo) throws JsonProcessingException {
        log.info("uploading a school form data :: {}",schoolFormData);
        if (logo == null){
            return schoolService.createBasicInfoWithoutFile(schoolFormData);
        }
        return schoolService.createBasicInfoWithFile(schoolFormData,logo);
    }
    @GetMapping("/schools")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_SCHOOL')")
    public ResponseDTO fetchSchools() throws JsonProcessingException {
        log.info("About to fetch school details");
        return schoolService.viewSchools();
    }
    @GetMapping("/school/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_SCHOOL')")
    public ResponseDTO getSchool(@PathVariable int id) throws JsonProcessingException {
        return schoolService.getSchool(id);
    }
    @PutMapping("/school/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_SCHOOL')")
    public ResponseDTO updateSchool(@PathVariable int id, @RequestBody SchoolDTO schoolDTO) throws JsonProcessingException {
        return schoolService.updateSchool(id,schoolDTO);
    }
//    @GetMapping("/per-agent")
//    public ResponseDTO schoolPerAgent(@RequestBody SchoolPerAgentDTO schoolPerAgentDTO)

    @DeleteMapping("/school/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('SOFT_DELETE_SCHOOL')")
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
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_SCHOOL_TYPE')")
    public ResponseDTO addSchoolType(@Valid  @PathParam("schoolType") String schoolType ) {
        log.info("About to save a schoolType basic info:{}",schoolType);
        return schoolService.addSchoolType(schoolType);
    }
    @GetMapping("/school-type")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_SCHOOL_TYPES')")
    public ResponseDTO getAllSchoolTypes() throws JsonProcessingException {
        return schoolService.getAllSchoolTypes();
    }
    @PutMapping("/school-type/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_SCHOOL_TYPE')")
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
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_SCHOOL_GENDER')")
    public ResponseDTO addSchoolGender( @Valid @RequestBody SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        return schoolService.addSchoolGender(schoolGenderDTO);
    }
    @GetMapping("/school-gender")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_SCHOOL_GENDERS')")
    public ResponseDTO getAllSchoolGenders() throws JsonProcessingException {
        return schoolService.getAllSchoolGenders();
    }
    @PutMapping("/school-gender/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_SCHOOL_GENDER')")
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
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_CURRICULUM')")
    public ResponseDTO addCurriculum(@Valid @RequestBody CurriculumDTO curriculumDTO) throws JsonProcessingException {
        return schoolService.addCurriculum(curriculumDTO);
    }
    @GetMapping("/curriculums")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_CURRICULUMS')")
    public ResponseDTO getAllCurriculums() throws JsonProcessingException {
        return schoolService.getCurriculums();
    }
    @PutMapping("/curriculum/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_CURRICULUM')")
    public ResponseDTO updateCurriculum(@PathVariable int id,@RequestBody CurriculumDTO curriculumDTO) throws JsonProcessingException {
        return schoolService.updateCurriculum(id,curriculumDTO);
    }
    @DeleteMapping("/del-curriculum/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('SOFT DELETE_CURRICULUM')")
    public ResponseDTO deleteCurriculum(@PathVariable int id){
        return schoolService.deleteCurriculum(id);
    }


    /**
     * CATEGORY
     * @param categoryDTO the request dto
     * @return the response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/category")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_CATEGORY')")
    public ResponseDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws JsonProcessingException {
        return  schoolService.addCategory(categoryDTO);
    }
    @PutMapping("/category/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_CATEGORY')")
    public ResponseDTO updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateCategory(categoryDTO,id);}

    @GetMapping("/category")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_CATEGORY')")
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
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_DESIGNATION')")
    public ResponseDTO addDesignation(@Valid @RequestBody DesignationDTO designationDTO) throws JsonProcessingException {
        return schoolService.addDesignation(designationDTO);
    }
    @PutMapping("/designation/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_DESIGNATION')")
    public ResponseDTO updateDesignation(@RequestBody DesignationDTO designationDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateDesignation(designationDTO,id);}

    @GetMapping("/designation")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_DESIGNATION')")
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

    @PostMapping("/school-contact")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_SCHOOL_CONTACTS')")
    public ResponseDTO addSchoolContacts( @Valid @RequestBody SchoolContactsDTO schoolContactsDTO) throws JsonProcessingException {
        return schoolService.createSchoolContact(schoolContactsDTO);
    }
    @PutMapping("/school-contact/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_SCHOOL_CONTACTS')")
    public ResponseDTO updateSchoolContact(@RequestBody SchoolContactsDTO schoolContactsDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateSchoolContacts(schoolContactsDTO,id);
    }

    @GetMapping("/school-contact/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_SCHOOL_CONTACT')")
    public ResponseDTO getSchoolContact(@PathVariable int id) throws JsonProcessingException {
    return schoolService.getSchoolContact(id);
    }

    @GetMapping("/school-contacts")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW SCHOOL_CONTACTS')")
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
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('DELETE_SCHOOL_CONTACTS')")
    public ResponseDTO deleteSchoolContacts(@PathVariable int id){
        return schoolService.deleteSchoolContacts(id);
    }


    /**
     * MENU CODES
     * @param documentTypeCodesDTO request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/menu-code")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_MENU_CODES')")
    public ResponseDTO saveMenuCode(@Valid @RequestBody DocumentTypeCodesDTO documentTypeCodesDTO) throws JsonProcessingException {
        return schoolService.saveMenuCode(documentTypeCodesDTO);
    }
    @PutMapping("/menu-code/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('UPDATE_MENU_CODES')")
    public ResponseDTO updateMenuCode(@RequestBody DocumentTypeCodesDTO documentTypeCodesDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateMenuCode(documentTypeCodesDTO,id);}

    @GetMapping("/menu-codes")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_MENU_CODES')")
    public ResponseDTO getAllMenuCodes() throws JsonProcessingException {
        return schoolService.getMenuCodes();
    }
    @GetMapping("/menu-code/{id}/document-type")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_DOCS_BY_MENU_CODES')")
    public ResponseDTO getByMenuCodeId(@PathVariable int id) throws JsonProcessingException {
        return schoolService.getDocumentTypeByMenuCodeId(id);
    }

    /**
     * SCHOOL DOCUMENTS
     * @param schoolDocumentData school document data
     * @param
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/school-doc")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_SCHOOL_DOC')")
    public ResponseDTO createSchoolDocument(@RequestPart ("documentData")String schoolDocumentData, @RequestPart ("fileDocs") List<MultipartFile> fileDocs ) throws JsonProcessingException {
        return schoolService.createDocument(schoolDocumentData,fileDocs);
    }
    @PutMapping("/school-doc/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_SCHOOL_DOC')")
    public ResponseDTO updateSchoolDocument(@RequestPart("schoolDocumentData") String schoolDocumentData, @RequestPart("fileDocs") MultipartFile fileDocs,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateSchoolDocument(schoolDocumentData,fileDocs,id);
    }
    @DeleteMapping("/school-doc/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('DELETE_SCHOOL_DOC')")
    public ResponseDTO deleteSchoolDocument(@PathVariable int id){
        return schoolService.deleteSchoolDocument(id);
    }


    /**
     * DIRECTORS
     * @param directorsRequestDTO  request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/create-director")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_DIRECTOR')")
    public ResponseDTO createDirector(@RequestBody DirectorsRequestDTO directorsRequestDTO) throws JsonProcessingException {
        return schoolService.createDirector(directorsRequestDTO);
    }

    @GetMapping("/directors")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_DIRECTOR')")
    public ResponseDTO getAllDirectors() throws JsonProcessingException {
        return schoolService.getAllDirectors();
    }
    @GetMapping("/single-director/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('VIEW_DIRECTOR')")
    public ResponseDTO getOneDirector(@PathVariable int id) throws JsonProcessingException {
        return schoolService.getDirector(id);
    }
    @PutMapping("/director/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('EDIT_DIRECTOR')")
    public ResponseDTO updateDirector(@RequestBody DirectorsRequestDTO directorsRequestDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateDirector(directorsRequestDTO,id);
    }

    @DeleteMapping("/del-director/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('SOFT DELETE_DIRECTOR')")
    public ResponseDTO deleteDirector(@PathVariable int id){
        return schoolService.deleteDirector(id);
    }



    @PostMapping("/submit-school")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('SUBMIT_SCHOOLS')")
    public ResponseDTO submitSchoolDataForApproval(@RequestBody SubmitSchoolDTO submitSchoolDTO){
        return schoolService.submitSchoolForApproval(submitSchoolDTO);
    }
    @PostMapping("/approve/school")
    @PreAuthorize("hasAnyRole(Partner) and hasAnyAuthority('APPROVE_SCHOOLS')")
    public ResponseDTO approveSchool(@RequestBody ApproveSchoolDTO approveSchoolDTO){
        return schoolService.approveSchool(approveSchoolDTO);
    }
    @PostMapping("/reject")
    @PreAuthorize("hasAnyRole(Partner) and hasAnyAuthority('REJECT_SCHOOLS')")
    public ResponseDTO rejectSchool(@RequestBody ApproveSchoolDTO approveSchoolDTO){
        return schoolService.rejectSchool(approveSchoolDTO);
    }

    //@PostMapping("/raise-clarification")
//    @PreAuthorize("hasAnyRole(Partner) and hasAnyAuthority('RAISE_CLARIFICATION')")

//    public ResponseDTO raiseClarification(@RequestBody ClarifyDTO clarifyDTO ){
//     return schoolService.raiseClarification(clarifyDTO);
//}
    @PostMapping("/reply-clarification")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('REPLY_ON_CLARIFICATION')")
    public ResponseDTO replyOnClarification(@RequestBody ReplyOnClarificationDTO replyOnClarificationDTO){
        return schoolService.replyOnClarification(replyOnClarificationDTO);
    }
    @PostMapping("/Close-replied-clarification")
    @PreAuthorize("hasAnyRole(Partner) and hasAnyAuthority('CLOSE_REPLIED_CLARIFICATION')")
    public ResponseDTO closeRepliedClarification(ClarifyDTO clarifyDTO){
        return schoolService.closeRepliedClarification(clarifyDTO);
    }








    /**
     * DOCUMENT TYPES
     * @param documentTypesDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/document-type")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_DOCUMENT_TYPES')")

    public ResponseDTO saveDocumentType(@Valid @RequestBody DocumentTypesDTO documentTypesDTO) throws JsonProcessingException {
        return schoolService.saveDocumentType(documentTypesDTO);
    }
    @PutMapping("/document-type/{id}")
    @PreAuthorize("hasAnyRole(Agent) and hasAnyAuthority('CREATE_MENU_CODES)")
    public ResponseDTO updateDocumentType(@RequestBody DocumentTypesDTO documentTypesDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateDocumentType(documentTypesDTO,id);}

    @GetMapping("/document-types")
    public ResponseDTO getAll() throws JsonProcessingException {
        return schoolService.getDocumentTypes();
    }



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
     * @return response dto
     * @throws JsonProcessingException the exception
     */


    @PostMapping("/support-docs")
    public ResponseDTO addSupportDocuments(@RequestPart("support") String support,@RequestPart("file") MultipartFile file) throws JsonProcessingException {
        return schoolService.CreateSupportDocuments(support,file);
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


    @PostMapping("/director-documents")
    public ResponseDTO createDirectorDocument(    @RequestPart("directors") String directors,
                                          @RequestPart("identityDoc") MultipartFile identityDoc,
                                          @RequestPart("pinCertificateDoc") MultipartFile pinCertificateDoc) throws JsonProcessingException {
return schoolService.createDirectorDocument(directors,identityDoc,pinCertificateDoc);
    }

    @PutMapping("/directors-doc/{id}")
    public ResponseDTO updateDirectorsDocument(@PathVariable int id,@RequestPart("directors") String directors,
                                               @RequestPart("identityDoc") MultipartFile identityDoc,
                                               @RequestPart("pinCertificateDoc") MultipartFile pinCertificateDoc) throws JsonProcessingException {
        return schoolService.updateDirectorsDocument(id,identityDoc,pinCertificateDoc,directors);
    }
 @DeleteMapping("/del-director-doc/{id}")
    public ResponseDTO deleteDirectorsDocument(@PathVariable int id){
        return schoolService.deleteDirectorsDocuments(id);
 }



}
