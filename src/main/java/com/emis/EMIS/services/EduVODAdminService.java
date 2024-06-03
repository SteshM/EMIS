package com.emis.EMIS.services;

import com.emis.EMIS.models.AgentInfoEntity;
import com.emis.EMIS.models.UserEntity;
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

    public ResponseDTO fetchAgents() {
      List<AgentInfoEntity>agentInfoEntityList=dataService.fetchAgents();
      log.info("fetched agents from the db{}",agentInfoEntityList);
        return utilities.successResponse("fetched all agents",agentInfoEntityList);

    }

    public ResponseDTO fetchByAgentId(int id) {
        var agentEntity = dataService.findByAgentId(id);
        return utilities.successResponse("fetched an agent",agentEntity);
    }

    public ResponseDTO updateAgentByAgentId(int id, UserDTO userDTO) {
        AgentInfoEntity agentInfo = dataService.findByAgentId(id);
        AgentInfoEntity agentInfo1 = modelMapper.map(userDTO, AgentInfoEntity.class);
        agentInfo1.setAgentType(agentInfo.getAgentType());
        dataService.saveAgent(agentInfo1);
        return utilities.successResponse("Updated an agent",agentInfo);
    }

}
