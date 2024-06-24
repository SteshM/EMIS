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

    @PostMapping("/school-contact/add")
    public ResponseDTO addSchoolContacts(@RequestBody SchoolContactsDTO schoolContactsDTO) throws JsonProcessingException {
        return schoolService.createSchoolContact(schoolContactsDTO);
    }
//    @PutMapping("/update/{id}")
//    public ResponseDTO updateSchoolContact(@RequestBody SchoolContactsDTO schoolContactsDTO,@PathVariable int id) throws JsonProcessingException {
//        return schoolService.updateSchoolContacts(schoolContactsDTO,id);
//    }
//
//    @GetMapping("/view/{id}")
//    public ResponseDTO getSchoolContact(@PathVariable int id){
//    return schoolService.getSchoolContact(id);
//    }
//
//    @GetMapping("/get-all")
//    public ResponseDTO viewSchoolContacts() throws JsonProcessingException {
//        return schoolService.viewSchoolContacts();
//    }
//
//    @GetMapping("/get-all/for-school")
//    public ResponseDTO viewSchoolContactsForSchool(){
//        return schoolService.getSchoolContactsForSchool();
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseDTO deleteSchoolContacts(@PathVariable int id){
//        return schoolService.deleteSchoolcontacts(id);
//    }
//
//

}
