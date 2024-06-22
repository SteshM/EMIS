package com.emis.EMIS.controllers;

import com.emis.EMIS.services.AgentService;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.SchoolDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/agent")
public class AgentController {
    private final AgentService agentService;

    /**
     * A method to enrol a school
     *
     * @param schoolDTO
     * @return agent Service
     */

    @PostMapping("/school")
    public ResponseDTO enrolSchool(@RequestBody SchoolDTO schoolDTO){
        log.info("Agent Controller : About to enrol a school :: {}",schoolDTO.getSchoolName());
        return agentService.enrolSchool(schoolDTO);
    }
    @GetMapping("/schools")
    public ResponseDTO fetchSchools()
    {
        log.info("About to fetch school details");
        return agentService.viewSchools();
    }
    @PutMapping("/school/{id}")
    public ResponseDTO updateSchool(@PathVariable int id, @RequestBody SchoolDTO schoolDTO){
        return agentService.updateSchool(id,schoolDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteSchool(@PathVariable int id){
        return agentService.deleteSchool(id);
    }

}
