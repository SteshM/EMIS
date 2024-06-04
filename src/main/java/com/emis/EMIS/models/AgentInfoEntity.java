package com.emis.EMIS.models;

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
    private int status;

    @OneToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;
}
