package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "otherAdmins")
public class SystemAdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  adminId;
    private String employmentNo;
    private String department;
    private String officePhoneNo;
    private Status status;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
}
