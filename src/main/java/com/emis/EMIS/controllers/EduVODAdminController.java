package com.emis.EMIS.controllers;

import com.emis.EMIS.services.EduVODAdminService;
import com.emis.EMIS.wrappers.AgentDTO;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.SchoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/eduAdmin")

public class EduVODAdminController {
    private final EduVODAdminService eduVODAdminService;

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
//
//
//
//    @GetMapping("/pending-schools")
//    public ResponseDTO pendingSchools(){
//        return eduVODAdminService.fetchPendingSchools();
//    }
//    @PostMapping("/approve-school")
//    public ResponseDTO approveSchool(@RequestBody SchoolDTO schoolDTO){
//        return .approveSchool(schoolDTO);
//    }
////    @GetMapping("/schools")
////    public ResponseDTO getSchools(){
////        return eduVODAdminService.fetchActiveSchools();
////    }
}
