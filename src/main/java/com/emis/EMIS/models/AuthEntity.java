package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "auth")
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authId;
    private String username;
    private String password;
    private Timestamp firstLogin;
    private Timestamp lastLogin;
    private int  failedLoginAttempts;
    private String status;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    UserEntity userEntity;
}
