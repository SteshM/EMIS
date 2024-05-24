package com.emis.EMIS.models;

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
    private String email;
    private String nationalId;
    private String phoneNo;
    private String password;
    private String otp;
    private String roles;
    @Column(nullable = false, updatable = false)
    private Date dateCreated = new Date();
    private String createdBy;
    private Date dateModified;
    private String modifiedBy;
    private int status = 1;

}

