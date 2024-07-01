package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="directorsEntity")
public class DirectorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int directorId;
    private String name;
    private String identityDoc;
    private String pinCertificateDoc;
    private Status status;


    @ManyToOne
    @JoinColumn(name = "documentTypesId")
    private DocumentTypes documentTypes;
    @ManyToOne
    @JoinColumn(name = "menuCodesId")
    private MenuCodes menuCodes;
    @ManyToOne
    @JoinColumn(name = "identityTypeId")
    private IdentityType identityType;
    private Integer identityNumber;
    private String pin;
    @ManyToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schoolsEntity;
    private int createdBy;
    private int deletedBy;
    private int updatedBy;

}
