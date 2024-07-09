package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "guardians")
public class GuardianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guardianId;
    private String relationship;
    private String occupation;
    private String emergencyContact;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;

}
