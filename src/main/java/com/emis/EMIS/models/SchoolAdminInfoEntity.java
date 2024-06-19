package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created By Stella
 */

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
    private Status status;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schools;
}
