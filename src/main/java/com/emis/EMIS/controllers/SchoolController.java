package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolService;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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
     *
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

    @PostMapping("county/save")
    public ResponseDTO addCounty(@RequestBody CountyDTO countyDTO) throws JsonProcessingException {
        return schoolService.addCounty(countyDTO);
    }
    @GetMapping("/county/get-all")
    public ResponseDTO getCounties() throws JsonProcessingException {
        return schoolService.getAllCounties();
    }
}
