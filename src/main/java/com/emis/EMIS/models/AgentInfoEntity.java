package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "agentInfo")
public class AgentInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agentInfoId;
    private String agencyName;
    private String jobTitle;
    private String emergencyContact;
    private Date lastActivity = new Date();
    @Column(nullable = false, updatable = false)
    private Date dateCreated = new Date();
    private String createdBy;
    private Date dateModified = new Date();
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;



}
