package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "schoolAdminInfo")
public class SchoolAdminInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolAdminId;
    private String adminRole;
    private String department;
    private String officePhone;
    private String tscNumber;
    private int schoolId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
}
