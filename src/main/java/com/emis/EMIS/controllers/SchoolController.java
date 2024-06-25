package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolService;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/agent")
public class SchoolController {
    private final SchoolService schoolService;

    /**
     * SCHOOL
     * A method to enrol a school
     * @param schoolDTO the dto
     * @return agent Service
     */

    @PostMapping("/school")
    public ResponseDTO enrolSchool(@RequestBody SchoolDTO schoolDTO) throws JsonProcessingException {
        log.info("School Controller : About to enrol a school :: {}",schoolDTO.getSchoolName());
        return schoolService.createBasicInfo(schoolDTO);
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
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteSchool(@PathVariable int id){
        return schoolService.deleteSchool(id);
    }



    /**
     * SCHOOL TYPE
     * @param schoolTypeDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/school-type/save")
    public ResponseDTO addSchoolType(@RequestBody SchoolTypeDTO schoolTypeDTO) throws JsonProcessingException {
        return schoolService.addSchoolType(schoolTypeDTO);
    }
    @GetMapping("/school-type/get-all")
    public ResponseDTO getAllSchoolTypes() throws JsonProcessingException {
        return schoolService.getAllSchoolTypes();
    }
    @PutMapping("/school-type/update/{id}")
    public ResponseDTO updateSchoolType(@PathVariable int id,@RequestBody SchoolTypeDTO schoolTypeDTO) throws JsonProcessingException {
        return schoolService.updateSchoolType(id,schoolTypeDTO);
    }


    /**
     * SCHOOL GENDER
     * @param schoolGenderDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/school-gender/save")
    public ResponseDTO addSchoolGender(@RequestBody SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        return schoolService.addSchoolGender(schoolGenderDTO);
    }
    @GetMapping("/school-gender/get-all")
    public ResponseDTO getAllSchoolGenders() throws JsonProcessingException {
        return schoolService.getAllSchoolGenders();
    }
    @PutMapping("/school-gender/update/{id}")
    public ResponseDTO updateSchoolGender(@PathVariable int id,@RequestBody SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        return schoolService.updateSchoolGender(id,schoolGenderDTO);
    }

    /**
     * CURRICULUM
     * @param curriculumDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/curriculum/save")
    public ResponseDTO addCurriculum(@RequestBody CurriculumDTO curriculumDTO) throws JsonProcessingException {
        return schoolService.addCurriculum(curriculumDTO);
    }
    @GetMapping("/curriculums/get-all")
    public ResponseDTO getAllCurriculums() throws JsonProcessingException {
        return schoolService.getCurriculums();
    }
    @PutMapping("/curriculum/update/{id}")
    public ResponseDTO updateCurriculum(@PathVariable int id,@RequestBody CurriculumDTO curriculumDTO) throws JsonProcessingException {
        return schoolService.updateCurriculum(id,curriculumDTO);
    }

    /**
     * COUNTY
     * @param countyDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("county/save")
    public ResponseDTO addCounty(@RequestBody CountyDTO countyDTO) throws JsonProcessingException {
        return schoolService.addCounty(countyDTO);
    }
    @PutMapping("/county/update/{id}")
    public ResponseDTO updateCounty(@RequestBody CountyDTO countyDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateCounty(countyDTO,id);
    }
    @GetMapping("/county/get-all")
    public ResponseDTO getCounties() throws JsonProcessingException {
        return schoolService.getAllCounties();
    }

    /**
     * SUB-COUNTY
     * @param subCountyDTO the request dto
     * @return the response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/subCounty/save")
    public ResponseDTO addSubCounty(@RequestBody SubCountyDTO subCountyDTO) throws JsonProcessingException {
        return  schoolService.addSubCounty(subCountyDTO);
    }
    @PutMapping("/subCounty/update/{id}")
    public ResponseDTO updateSubCounty(@RequestBody SubCountyDTO subCountyDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateSubCounty(subCountyDTO,id);}

    @GetMapping("/subCounty/all")
    public ResponseDTO getSubCounties() throws JsonProcessingException {
        return schoolService.getAllSubCounties();
    }

    /**
     * CATEGORY
     * @param categoryDTO the request dto
     * @return the response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/category/add")
    public ResponseDTO addCategory(@RequestBody CategoryDTO categoryDTO) throws JsonProcessingException {
        return  schoolService.addCategory(categoryDTO);
    }
    @PutMapping("/category/update/{id}")
    public ResponseDTO updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateCategory(categoryDTO,id);}

    @GetMapping("/category/all")
    public ResponseDTO getCategories() throws JsonProcessingException {
        return schoolService.getCategories();
    }

    /**
     * DESIGNATION
     * @param designationDTO request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/designation/add")
    public ResponseDTO addDesignation(@RequestBody DesignationDTO designationDTO) throws JsonProcessingException {
        return schoolService.addDesignation(designationDTO);
    }
    @PutMapping("/designation/update/{id}")
    public ResponseDTO updateDesignation(@RequestBody DesignationDTO designationDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateDesignation(designationDTO,id);}

    @GetMapping("/designation/all")
    public ResponseDTO getDesignations() throws JsonProcessingException {
        return schoolService.getDesignations();
    }

    /**
     * DIOCESE
     * @param dioceseDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/diocese/add")
    public ResponseDTO addDiocese(@RequestBody DioceseDTO dioceseDTO) throws JsonProcessingException {
        return schoolService.addDiocese(dioceseDTO);
    }
    @PutMapping("/diocese/update/{id}")
    public ResponseDTO updateDiocese(@RequestBody DioceseDTO dioceseDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateDiocese(dioceseDTO,id);}

    @GetMapping("/diocese/all")
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
    @PutMapping("/update/{id}")
    public ResponseDTO updateSchoolContact(@RequestBody SchoolContactsDTO schoolContactsDTO,@PathVariable int id) throws JsonProcessingException {
        return schoolService.updateSchoolContacts(schoolContactsDTO,id);
    }

    @GetMapping("/view/{id}")
    public ResponseDTO getSchoolContact(@PathVariable int id) throws JsonProcessingException {
    return schoolService.getSchoolContact(id);
    }

    @GetMapping("/get-all")
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

    @PostMapping("/create/identity-type")
    public ResponseDTO createIdentityType(@RequestBody IdentityTypeDTO identityTypeDTO) throws JsonProcessingException {
        return schoolService.createIdentityType(identityTypeDTO);
    }
    @PutMapping("/identity-type/update/{id}")
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




    @PostMapping("/support-docs/save")
    public ResponseDTO addSupportDocuments(@RequestBody SupportDocDTO supportDocDTO) throws JsonProcessingException {
        return schoolService.CreateSupportDocuments(supportDocDTO);
    }

    @GetMapping("/get-all/{id}")
    public ResponseDTO getALlBySupportId(@PathVariable int id) throws JsonProcessingException {
        return schoolService.getAllBySupportId(id);
    }

    @GetMapping("/get-all/support-docs")
    public ResponseDTO getAllSupportDocs() throws JsonProcessingException {
        return schoolService.getAllSupportDocs();
    }
//
//    @DeleteMapping("/delete/support-docs/{id}")
//    public ResponseDTO deleteSupportDocs(@PathVariable int id){
//        return schoolService.deleteSupportDocs(id);
//    }




}
