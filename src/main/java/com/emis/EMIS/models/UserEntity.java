package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String nationalId;
    private String phoneNo;
    private  String password;
    @Column(nullable = false, updatable = false)
    private Date dateCreated = new Date();
    private String createdBy;
    private Date dateModified =new Date();
    private String modifiedBy;
    private int status;
    private Date lastActivity = new Date();
    private Timestamp firstLogin;
    private Timestamp lastLogin;
    private int failedLoginAttempts;
    private int profileId;



}

