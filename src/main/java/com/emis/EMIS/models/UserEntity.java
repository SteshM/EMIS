package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String gender;
    private String nationality;
    private String dateOfBirth;
    private String phoneNo;
    private  String password;
    @Column(nullable = false, updatable = false)
    private Date dateCreated = new Date();
    private String createdBy;
    private Date dateModified;
    private String modifiedBy;
    private Status status;
    private Date lastActivity = new Date();
    private Date firstLogin;
    private Date lastLogin;
    private int failedLoginAttempts;
    private int profileId;
    private String profilePic;



}

