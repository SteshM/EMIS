package com.emis.EMIS.services;
import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.PageRequestDTO;
import com.emis.EMIS.wrappers.responseDTOs.AgentDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.SystemAdminsDTO;
import com.emis.EMIS.wrappers.responseDTOs.PartnerDTO;
import com.emis.EMIS.wrappers.responseDTOs.SchoolAdminDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EduVODAdminService {
    private final ModelMapper modelMapper;
    private final DataService dataService;
    private final Utilities utilities;

    //Other-System-Admins

    public ResponseDTO viewSystemAdmins() throws JsonProcessingException {
        List<SystemAdminEntity>systemAdminEntityList = dataService.viewAll();
        List<SystemAdminsDTO>otherAdminsDTOList = systemAdminEntityList.stream()
                .map(systemAdmin -> {
                  return modelMapper.map(systemAdmin,SystemAdminsDTO.class);

                })
                .toList();
        log.info("Fetched  all System Admins' Details:{}", new ObjectMapper().writeValueAsString(systemAdminEntityList));
        return utilities.successResponse("Fetched active admins",otherAdminsDTOList);
    }


    public ResponseDTO singleAdmin(int id) throws JsonProcessingException {
        var systemAdmin = dataService.findByAdminId(id);
        var systemAdminsDTO =modelMapper.map(systemAdmin,SystemAdminsDTO.class);
        log.info("Fetched student Details:{}", new ObjectMapper().writeValueAsString(systemAdmin));
        return utilities.successResponse("fetched a single admin",systemAdminsDTO);
    }

    public ResponseDTO updateAdminDetails(int id, SystemAdminsDTO systemAdminsDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var systemAdmin = dataService.findByAdminId(id);
        log.info("Fetched an admin:{}", objectMapper.writeValueAsString(systemAdmin));
        modelMapper.map(systemAdminsDTO,systemAdmin);
        log.info("Updated admins Details. About to save:{}", objectMapper.writeValueAsString(systemAdmin));
        dataService.saveSystemAdmin(systemAdmin);
        return utilities.successResponse("updated admins details",systemAdminsDTO);
    }

    public ResponseDTO deleteAdmin(int id) {
        var systemAdmin = dataService.findByAdminId(id);
        systemAdmin.setStatus(Status.DELETED);
        systemAdmin.getUserEntity().setStatus(Status.DELETED);
        dataService.saveSystemAdmin(systemAdmin);
        return utilities.successResponse("soft deleted an admin",null);
    }


    //School-Admins
    public ResponseDTO fetchActiveSchoolAdmins() throws JsonProcessingException {
        List<SchoolAdminInfoEntity>schoolAdminInfoEntities = dataService.fetchActiveSchoolAdmins();
        log.info("About to fetch active school admins {}",schoolAdminInfoEntities);
        List<SchoolAdminDTO>schoolAdminDTOList = schoolAdminInfoEntities.stream()
                .map(schoolAdminInfo -> {
                    return modelMapper.map(schoolAdminInfo,SchoolAdminDTO.class);
                })
                .toList();
        log.info("Fetched  all School admin Details:{}", new ObjectMapper().writeValueAsString(schoolAdminInfoEntities));
        return utilities.successResponse("Successfully fetched active school admins",schoolAdminDTOList);
    }


    public ResponseDTO fetchSchoolAdminById(int id) throws JsonProcessingException {
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        log.info("Fetched school admin Details:{}", new ObjectMapper().writeValueAsString(schoolAdminInfo));
        var schoolAdminDTO = modelMapper.map(schoolAdminInfo, SchoolAdminDTO.class);
        return utilities.successResponse("fetched a school admin",schoolAdminDTO);

    }

    public ResponseDTO updateSchoolAdminDetails(int id, SchoolAdminDTO schoolAdminDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        log.info("about to fetch  an admin{}",objectMapper.writeValueAsString(schoolAdminInfo));
        modelMapper.map(schoolAdminDTO,schoolAdminInfo);
        log.info("Updated  school admins Details. About to save:{}", objectMapper.writeValueAsString(schoolAdminInfo));
        dataService.saveSchoolAdmin(schoolAdminInfo);
        return utilities.successResponse("updated  school admin details",schoolAdminDTO);

    }
    public ResponseDTO deleteSchoolAdmin(int id){
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        schoolAdminInfo.setStatus(Status.DELETED);
        schoolAdminInfo.getUserEntity().setStatus(Status.DELETED);
        dataService.saveSchoolAdmin(schoolAdminInfo);
        return utilities.successResponse("deleted a school admin",null);
    }


    //Agents

    public ResponseDTO fetchActiveAgents(PageRequestDTO pageRequestDTO) throws JsonProcessingException {
        var pageable = PageRequest.of(pageRequestDTO.getPageNo(),pageRequestDTO.getPageSize());
        var sort = Sort.by(Sort.Direction.valueOf(pageRequestDTO.getDirection().toUpperCase()),pageRequestDTO.getOrderBy());
        Page<AgentInfoEntity>agentInfoEntityList=dataService.fetchActiveAgents(pageable);
        log.info("Fetched agents from the db:{}",agentInfoEntityList);
        List<AgentDTO> agentDTOList = agentInfoEntityList.stream()
                .map(agentInfoEntity -> {
                    return modelMapper.map(agentInfoEntity, AgentDTO.class);

                })
                .toList();
    log.info("About to fetch all active agents' Details:{}", new ObjectMapper().writeValueAsString(agentInfoEntityList));
    return utilities.successResponse("fetched all agents",agentDTOList);}



    public ResponseDTO fetchByAgentId(int id) throws JsonProcessingException {
        var agentEntity = dataService.findByAgentId(id);
        log.info("Fetched agent Details:{}", new ObjectMapper().writeValueAsString(agentEntity));
        var agentDTO = modelMapper.map(agentEntity, AgentDTO.class);
        return utilities.successResponse("fetched an agent",agentDTO);
    }

    public ResponseDTO updateAgentByAgentId(int id, AgentDTO agentDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var agentInfo = dataService.findByAgentId(id);
        log.info("Fetching an agent's details{}",objectMapper.writeValueAsString(agentInfo));
        modelMapper.map(agentDTO,agentInfo);
        log.info("Updated agent Details. About to save:{}", objectMapper.writeValueAsString(agentInfo));
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("Updated an agent",agentDTO);
    }

    public ResponseDTO softDeleteAgent(int id) {
        var agentInfo = dataService.findByAgentId(id);
        agentInfo.setStatus(Status.DELETED);
        agentInfo.getUserEntity().setStatus(Status.DELETED);
        log.info("changed agent's status to deleted {}",agentInfo);
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("soft deleted agent",null);
    }

    //Partners

    public ResponseDTO viewActivePartners() throws JsonProcessingException {
        List<PartnerInfoEntity>partnerInfoEntityList = dataService.fetchActivePartners();
        log.info("about to fetch active partners from te db : {}",partnerInfoEntityList);
        List<PartnerDTO>partnerDTOList = partnerInfoEntityList.stream()
                .map(partnerInfoEntity ->{
                    return modelMapper.map(partnerInfoEntity,PartnerDTO.class);
                })
                .toList();
        log.info("Fetching all active partners' Details:{}", new ObjectMapper().writeValueAsString(partnerInfoEntityList));
        return utilities.successResponse("Successfully fetched active partners",partnerDTOList);
    }

    public ResponseDTO fetchOne(int id) throws JsonProcessingException {
        var partnerInfo = dataService.findByPartnerId(id);
        log.info("Fetching a partner's Details:{}", new ObjectMapper().writeValueAsString(partnerInfo));
        var partnerDTO = modelMapper.map(partnerInfo, PartnerDTO.class);
        return utilities.successResponse("Successfully fetched a partner",partnerDTO);
    }


    public ResponseDTO updatePartnerDetails(int id, PartnerDTO partnerDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var partnerInfo = dataService.findByPartnerId(id);
        log.info("About to fetch a partner's details{}",objectMapper.writeValueAsString(partnerInfo));
        modelMapper.map(partnerDTO,partnerInfo);
        log.info("Updated a Partner's Details. About to save:{}", objectMapper.writeValueAsString(partnerInfo));
        dataService.savePartner(partnerInfo);
        return utilities.successResponse("Successfully updated a partners details",partnerDTO);

    }

    public ResponseDTO deletePartner(int id) {
        var partnerInfo = dataService.findByPartnerId(id);
        partnerInfo.setStatus(Status.DELETED);
        partnerInfo.getUserEntity().setStatus(Status.DELETED);
        dataService.savePartner(partnerInfo);
        return utilities.successResponse("deleted a partner",null);

    }

}
