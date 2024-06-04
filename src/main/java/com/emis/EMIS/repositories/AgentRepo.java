package com.emis.EMIS.repositories;

import com.emis.EMIS.models.AgentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepo extends JpaRepository<AgentInfoEntity,Integer> {
    AgentInfoEntity findByAgentId(int agentId);
    List<AgentInfoEntity> findByStatus(int status);
}
