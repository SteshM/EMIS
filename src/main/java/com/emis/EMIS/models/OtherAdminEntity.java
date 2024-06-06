package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "otherAdmins")
public class OtherAdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  adminId;
    private String employmentNo;
    private String department;
    private String officePhoneNo;
}
