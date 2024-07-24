package com.emis.EMIS.controllers;

import com.emis.EMIS.services.EduVODAdminService;
import com.emis.EMIS.wrappers.requestDTOs.*;
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
@CrossOrigin("*")

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
    public ResponseDTO updateAdminDetails(@PathVariable int id, @RequestBody UpdateAdminDTO updateAdminDTO) throws JsonProcessingException {
        return eduVODAdminService.updateAdminDetails(id,updateAdminDTO);
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
    public ResponseDTO updateSchool(@PathVariable int id, @RequestBody UpdateSchAdminDTO updateSchAdminDTO) throws JsonProcessingException {
        return eduVODAdminService.updateSchoolAdminDetails(id,updateSchAdminDTO);
    }
    @DeleteMapping("/del-school-admin/{id}")
    public ResponseDTO deleteSchoolAdm(@PathVariable int id){
        return eduVODAdminService.deleteSchoolAdmin(id);
    }



    //Agents

    @GetMapping("/agents")
    public ResponseDTO fetchAll() throws JsonProcessingException {
        return eduVODAdminService.fetchActiveAgents();
    }

  @GetMapping("/agent/{id}")
    public ResponseDTO fetchAgentById(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.fetchByAgentId(id);
  }
  @PutMapping("/update-agent/{id}")
    public ResponseDTO updateAgent(@PathVariable int id , @RequestBody UpdateAgentDTO updateAgentDTO) throws JsonProcessingException
  {return eduVODAdminService.updateAgentByAgentId(id,updateAgentDTO);
  }
  @DeleteMapping("/agent/{id}")
    public ResponseDTO softDelete(@PathVariable int id){
        return eduVODAdminService.softDeleteAgent(id);
  }



  //Partners
    @GetMapping("/partners")
    public ResponseDTO viewActivePartners() throws JsonProcessingException {
        return eduVODAdminService.viewActivePartners();
    }

    @GetMapping("/partner/{id}")
    public ResponseDTO fetchOne(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.fetchOne(id);
    }

    @PutMapping("/partner/{id}")
    public ResponseDTO updatePartnerDetails(@PathVariable int id, @RequestBody UpdatePartnerDTO updatePartnerDTO) throws JsonProcessingException {
        return eduVODAdminService.updatePartnerDetails(id,updatePartnerDTO);
    }
    @DeleteMapping("/del-partner/{id}")
    public ResponseDTO delPartner(@PathVariable int id){
        return eduVODAdminService.deletePartner(id);
    }


}
