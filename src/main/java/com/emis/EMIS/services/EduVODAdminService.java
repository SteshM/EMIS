package com.emis.EMIS.services;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.AgentInfoEntity;
import com.emis.EMIS.models.SchoolsEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.AgentDTO;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.SchoolDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EduVODAdminService {
    private final ModelMapper modelMapper;
    private final DataService dataService;
    private final Utilities utilities;
    private final UserConfigs userConfigs;

    public ResponseDTO fetchActiveAgents() {
        List<AgentInfoEntity>agentInfoEntityList=dataService.fetchActiveAgents();
        log.info("Fetched agents from the db:{}",agentInfoEntityList);
        List<AgentDTO> agentDTOList = agentInfoEntityList.stream()
                .map(agentInfoEntity -> {
                  // return modelMapper.map(agentInfoEntity, AgentDTO.class);
                  return   AgentDTO.builder()
                            .agentType(agentInfoEntity.getAgentType())
                            .agencyName(agentInfoEntity.getAgencyName())
                            .emergencyContact(agentInfoEntity.getEmergencyContact())
                            .firstName(agentInfoEntity.getUserEntity().getFirstName())
                            .middleName(agentInfoEntity.getUserEntity().getMiddleName())
                            .lastName(agentInfoEntity.getUserEntity().getLastName())
                            .nationalId(agentInfoEntity.getUserEntity().getNationalId())
                            .email(agentInfoEntity.getUserEntity().getEmail())
                            .phoneNo(agentInfoEntity.getUserEntity().getPhoneNo())
                            .build();
                })
                .toList();

        return utilities.successResponse("fetched all agents",agentDTOList);}

    public ResponseDTO fetchByAgentId(int id) {
        var agentEntity = dataService.findByAgentId(id);
        return utilities.successResponse("fetched an agent",agentEntity);
    }

    public ResponseDTO updateAgentByAgentId(int id, AgentDTO agentDTO) {
        AgentInfoEntity agentInfo = dataService.findByAgentId(id);
        agentInfo.setAgentType(agentDTO.getAgentType());
        agentInfo.setAgencyName(agentDTO.getAgencyName());
        agentInfo.setEmergencyContact(agentDTO.getEmergencyContact());
        AgentDTO agentDTO1 =modelMapper.map(agentInfo,AgentDTO.class);
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("Updated an agent",agentDTO1);
    }


    public ResponseDTO softDeleteAgent(int id) {
        AgentInfoEntity agentInfo = dataService.findByAgentId(id);
        agentInfo.setStatus(Status.DELETED);
        agentInfo.getUserEntity().setStatus(userConfigs.getDeletedStatus());
        log.info("changed agent's status to deleted {}",agentInfo);
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("soft deleted agent",null);
    }

    public ResponseDTO createSchool(SchoolDTO schoolDTO) {
        SchoolsEntity schools = modelMapper.map(schoolDTO,SchoolsEntity.class);
        dataService.saveSchool(schools);
        log.info("About to fetch the saved schools {}",schools);
        return utilities.successResponse("Created school",schools);
    }

//    public ResponseDTO fetchActiveSchools() {
//    }
}
