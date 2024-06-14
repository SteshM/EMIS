package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Data;

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

}
