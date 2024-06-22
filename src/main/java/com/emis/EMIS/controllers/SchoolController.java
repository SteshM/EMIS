package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolService;
import com.emis.EMIS.wrappers.requestDTOs.SchoolTypeDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.SchoolDTO;
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
     * A method to enrol a school
     *
     * @param schoolDTO
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
}
