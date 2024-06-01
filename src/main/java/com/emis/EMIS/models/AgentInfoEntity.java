package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "agentInfo")
public class AgentInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agentInfoId;
    private String agencyName;
    private String agentType;
    private String jobTitle;
    private String emergencyContact;
    private int userId;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;



}
