package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "guardians")
public class GuardianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guardianId;
    private String relationship;
    private String occupation;
    private String emergencyContact;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;

}
