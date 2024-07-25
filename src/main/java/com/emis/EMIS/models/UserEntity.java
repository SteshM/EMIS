package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

/**
 * Created by Stella
 * Project Eduvod-Emis system
 * Date: 12/05/2024
 */

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date() ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified ;
    private String modifiedBy;
    private Status status;
    private Date lastActivity = new Date();
    private Date firstLogin = new Date();
    private Date lastLogin;
    private int failedLoginAttempts;
    private int profileId;
    private String profilePic;

    @OneToMany
    private List<ProfileEntity> profiles;

    @OneToMany
    private List<AgentInfoEntity>agentInfoEntities;

    @OneToMany
    private List<PartnerInfoEntity>partnerInfoEntities;

    @OneToMany
    private List<SystemAdminEntity>systemAdminEntities;

    @OneToMany
    private List<SchoolAdminInfoEntity>schoolAdminInfoEntities;

   @OneToMany
    private List<StudentEntity>studentEntities;

   @OneToMany
    private List<TeacherEntity>teacherEntities;




}

