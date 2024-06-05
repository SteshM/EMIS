package com.emis.EMIS.controllers;

import com.emis.EMIS.services.EduVODAdminService;
import com.emis.EMIS.wrappers.AgentDTO;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.SchoolDTO;
import com.emis.EMIS.wrappers.responseDTOs.SchoolAdminDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/eduAdmin")

public class EduVODAdminController {
    private final EduVODAdminService eduVODAdminService;

    @GetMapping("/school-admins")
    public ResponseDTO fetchSchoolAdmins(){
        return eduVODAdminService.fetchActiveSchoolAdmins();
    }
    @GetMapping("/school-admin/{id}")
    public ResponseDTO fetchSchoolAdminById(@PathVariable int id){
        return eduVODAdminService.fetchSchoolAdminById(id);
    }
    @PutMapping("/school-admin/{id}")
    public ResponseDTO updateSchool(@PathVariable int id, @RequestBody SchoolAdminDTO schoolAdminDTO){
        return eduVODAdminService.updateSchool(id,schoolAdminDTO);
    }



    @GetMapping("/agents")
    public ResponseDTO fetchAll(){
        return eduVODAdminService.fetchActiveAgents();
    }

  @GetMapping("/agent/{id}")
    public ResponseDTO fetchAgentById(@PathVariable int id){
        return eduVODAdminService.fetchByAgentId(id);
  }
  @PutMapping("/update-agent/{id}")
    public ResponseDTO updateAgent(@PathVariable int id , @RequestBody AgentDTO agentDTO){
        return eduVODAdminService.updateAgentByAgentId(id,agentDTO);
  }
  @DeleteMapping("/agent/{id}")
    public ResponseDTO softDelete(@PathVariable int id){
        return eduVODAdminService.softDeleteAgent(id);
  }

}
