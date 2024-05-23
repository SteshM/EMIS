package com.emis.EMIS.models;

import com.emis.EMIS.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Collection;

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
    private Collection<Role> roles;
//    @Column(nullable = false, updatable = false)
//    private Date dateCreated;
//    private String createdBy;
//    @Column(nullable = false)
//    private Date dateModified;
//    private String modifiedBy;
//    private String status;

}

