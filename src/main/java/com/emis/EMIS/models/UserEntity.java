package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private int nationalId;
    private int phoneNo;
    private String password;
    private int otp;
    private String roles;
//    @Column(nullable = false, updatable = false)
//    private Date dateCreated;
//    private String createdBy;
//    @Column(nullable = false)
//    private Date dateModified;
//    private String modifiedBy;
//    private String status;

}

