package com.emis.EMIS.models;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "agentInfo")
public class AgentInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agentId;
    private String agencyName;
    private String agentType;
    private String emergencyContact;
    private Status status;

    @OneToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;
}
