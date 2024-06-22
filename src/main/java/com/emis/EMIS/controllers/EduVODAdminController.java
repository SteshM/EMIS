package com.emis.EMIS.controllers;

import com.emis.EMIS.services.EduVODAdminService;
import com.emis.EMIS.wrappers.requestDTOs.PageRequestDTO;
import com.emis.EMIS.wrappers.responseDTOs.AgentDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.SystemAdminsDTO;
import com.emis.EMIS.wrappers.responseDTOs.PartnerDTO;
import com.emis.EMIS.wrappers.responseDTOs.SchoolAdminDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/eduAdmin")

public class EduVODAdminController {
    private final EduVODAdminService eduVODAdminService;

    //OtherAdmins
    @GetMapping("/admins")
    public ResponseDTO viewSystemAdmins() throws JsonProcessingException {
        return eduVODAdminService.viewSystemAdmins();
    }
    @GetMapping("/single-admin/{id}")
    public ResponseDTO singleAdmin(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.singleAdmin(id);
    }
    @PutMapping("/admin/{id}")
    public ResponseDTO updateAdminDetails(@PathVariable int id, @RequestBody SystemAdminsDTO systemAdminsDTO) throws JsonProcessingException {
        return eduVODAdminService.updateAdminDetails(id,systemAdminsDTO);
    }
    @DeleteMapping("/del-admin/{id}")
    public ResponseDTO deleteAdmin(@PathVariable int id){
        return eduVODAdminService.deleteAdmin(id);
    }



    //School-Admins

    @GetMapping("/school-admins")
    public ResponseDTO fetchSchoolAdmins() throws JsonProcessingException {
        return eduVODAdminService.fetchActiveSchoolAdmins();
    }
    @GetMapping("/school-admin/{id}")
    public ResponseDTO fetchSchoolAdminById(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.fetchSchoolAdminById(id);
    }
    @PutMapping("/school-admin/{id}")
    public ResponseDTO updateSchool(@PathVariable int id, @RequestBody SchoolAdminDTO schoolAdminDTO) throws JsonProcessingException {
        return eduVODAdminService.updateSchoolAdminDetails(id,schoolAdminDTO);
    }
    @DeleteMapping("/del-school-admin/{id}")
    public ResponseDTO deleteSchoolAdm(@PathVariable int id){
        return eduVODAdminService.deleteSchoolAdmin(id);
    }



    //Agents

    @GetMapping("/agents")
    public ResponseDTO fetchAll(@RequestBody PageRequestDTO pageRequestDTO) throws JsonProcessingException {
        return eduVODAdminService.fetchActiveAgents(pageRequestDTO);
    }

  @GetMapping("/agent/{id}")
    public ResponseDTO fetchAgentById(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.fetchByAgentId(id);
  }
  @PutMapping("/update-agent/{id}")
    public ResponseDTO updateAgent(@PathVariable int id , @RequestBody AgentDTO agentDTO) throws JsonProcessingException {return eduVODAdminService.updateAgentByAgentId(id,agentDTO);
  }
  @DeleteMapping("/agent/{id}")
    public ResponseDTO softDelete(@PathVariable int id){
        return eduVODAdminService.softDeleteAgent(id);
  }



  //Partners
    @GetMapping("/partners")
    public ResponseDTO viewActivePartners(@RequestBody PageRequestDTO pageRequestDTO) throws JsonProcessingException {
        return eduVODAdminService.viewActivePartners(pageRequestDTO);
    }

    @GetMapping("/partner/{id}")
    public ResponseDTO fetchOne(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.fetchOne(id);
    }

    @PutMapping("/partner/{id}")
    public ResponseDTO updatePartnerDetails(@PathVariable int id, @RequestBody PartnerDTO partnerDTO) throws JsonProcessingException {
        return eduVODAdminService.updatePartnerDetails(id,partnerDTO);
    }
    @DeleteMapping("/del-partner/{id}")
    public ResponseDTO delPartner(@PathVariable int id){
        return eduVODAdminService.deletePartner(id);
    }

}
