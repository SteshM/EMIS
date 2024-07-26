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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole(SuperAdmin) and hasAnyAuthority('VIEW_SYSTEM_ADMINS')")
    public ResponseDTO viewSystemAdmins() throws JsonProcessingException {
        return eduVODAdminService.viewSystemAdmins();
    }
    @GetMapping("/single-admin/{id}")
    @PreAuthorize("hasAnyRole(SuperAdmin) and hasAnyAuthority('VIEW_SYSTEM_ADMIN')")
    public ResponseDTO singleAdmin(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.singleAdmin(id);
    }
    @PutMapping("/admin/{id}")
    @PreAuthorize("hasAnyRole(SuperAdmin) and hasAnyAuthority('EDIT_SYSTEM_ADMIN')")
    public ResponseDTO updateAdminDetails(@PathVariable int id, @RequestBody UpdateAdminDTO updateAdminDTO) throws JsonProcessingException {
        return eduVODAdminService.updateAdminDetails(id,updateAdminDTO);
    }
    @PreAuthorize("hasAnyRole(SuperAdmin) and hasAnyAuthority('SOFT DELETE_SYSTEM_ADMIN')")
    @DeleteMapping("/del-admin/{id}")
    public ResponseDTO deleteAdmin(@PathVariable int id){
        return eduVODAdminService.deleteAdmin(id);
    }



    //School-Admins

    @GetMapping("/school-admins")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_SCHOOL_ADMINS')")
    public ResponseDTO fetchSchoolAdmins() throws JsonProcessingException {
        return eduVODAdminService.fetchActiveSchoolAdmins();
    }
    @GetMapping("/school-admin/{id}")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_SCHOOL_ADMIN')")
    public ResponseDTO fetchSchoolAdminById(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.fetchSchoolAdminById(id);
    }
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('EDIT_SCHOOL_ADMIN')")
    @PutMapping("/school-admin/{id}")
    public ResponseDTO updateSchool(@PathVariable int id, @RequestBody UpdateSchAdminDTO updateSchAdminDTO) throws JsonProcessingException {
        return eduVODAdminService.updateSchoolAdminDetails(id,updateSchAdminDTO);
    }
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('SOFT DELETE_SCHOOL_ADMIN')")
    @DeleteMapping("/del-school-admin/{id}")
    public ResponseDTO deleteSchoolAdm(@PathVariable int id){
        return eduVODAdminService.deleteSchoolAdmin(id);
    }



    //Agents

    @GetMapping("/agents")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_AGENTS')")
    public ResponseDTO fetchAll() throws JsonProcessingException {
        return eduVODAdminService.fetchActiveAgents();
    }

  @GetMapping("/agent/{id}")
  @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_AGENTS')")
  public ResponseDTO fetchAgentById(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.fetchByAgentId(id);
  }
  @PutMapping("/update-agent/{id}")
  @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('EDIT_AGENTS')")
  public ResponseDTO updateAgent(@PathVariable int id , @RequestBody UpdateAgentDTO updateAgentDTO) throws JsonProcessingException
  {return eduVODAdminService.updateAgentByAgentId(id,updateAgentDTO);
  }
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('SOFT DELETE_AGENTS')")
    @DeleteMapping("/agent/{id}")
    public ResponseDTO softDelete(@PathVariable int id){
        return eduVODAdminService.softDeleteAgent(id);
  }



  //Partners
    @GetMapping("/partners")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_PARTNERS')")
    public ResponseDTO viewActivePartners() throws JsonProcessingException {
        return eduVODAdminService.viewActivePartners();
    }

    @GetMapping("/partner/{id}")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_PARTNER')")
    public ResponseDTO fetchOne(@PathVariable int id) throws JsonProcessingException {
        return eduVODAdminService.fetchOne(id);
    }

    @PutMapping("/partner/{id}")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('EDIT_PARTNERS')")
    public ResponseDTO updatePartnerDetails(@PathVariable int id, @RequestBody UpdatePartnerDTO updatePartnerDTO) throws JsonProcessingException {
        return eduVODAdminService.updatePartnerDetails(id,updatePartnerDTO);
    }
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('SOFT DELETE_PARTNERS')")
    @DeleteMapping("/del-partner/{id}")
    public ResponseDTO delPartner(@PathVariable int id){
        return eduVODAdminService.deletePartner(id);
    }


    /**
     * RESOURCES
     * @param resourceDTO
     * @return
     * @throws JsonProcessingException
     */

    @PostMapping("/resource")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('CREATE_RESOURCES)'")
    public ResponseDTO addResource(@Valid @RequestBody ResourceDTO resourceDTO) throws JsonProcessingException {
        return eduVODAdminService.createResource(resourceDTO);
    }
    @GetMapping("/resources")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_RESOURCES')")
    public ResponseDTO fetchResources(){
        return eduVODAdminService.AllResources();
    }




}
