package com.emis.EMIS.services;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.models.AgentInfoEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.AgentDTO;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EduVODAdminService {
    private final ModelMapper modelMapper;
    private final DataService dataService;
    private final Utilities utilities;
    private final UserConfigs userConfigs;

    public ResponseDTO fetchAgents() {
      List<AgentInfoEntity>agentInfoEntityList=dataService.fetchAgents();
      log.info("Fetched agents from the db:{}",agentInfoEntityList);
        return utilities.successResponse("fetched all agents",agentInfoEntityList);
    }

    public ResponseDTO fetchByAgentId(int id) {
        var agentEntity = dataService.findByAgentId(id);
        return utilities.successResponse("fetched an agent",agentEntity);
    }

    public ResponseDTO updateAgentByAgentId(int id, UserDTO userDTO) {
        AgentInfoEntity agentInfo = dataService.findByAgentId(id);
        modelMapper.map(agentInfo, AgentDTO.class);
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("Updated an agent",agentInfo);
    }

    public ResponseDTO softDeleteAgent(int id) {
        AgentInfoEntity agentInfo = dataService.findByAgentId(id);
        agentInfo.getUserEntity().setStatus();
        log.info("changed agent's status to deleted {}",agentInfo);
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("soft deleted agent",null);
    }
}
