package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agentInfo")
@Builder

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
