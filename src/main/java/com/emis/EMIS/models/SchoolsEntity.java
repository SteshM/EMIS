package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "schools")
public class SchoolsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;
    private String schoolName;
    private String moeRegistrationNo;
    private String MobileNo;
    private String emailAddress;
    private String postalAddress;
    private String postalCode;
    private Status status;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date() ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    private String modifiedBy;


    @ManyToOne
    @JoinColumn(name = "schoolTypeId")
    private SchoolType schoolType;

}
