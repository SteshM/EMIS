package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "universalDocumentEntity")
public class UniversalDocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int universalDocId;
    private String docName;
    private String docUrl;
    private String docSize;
    private String docType;
    private String docKey;


    @ManyToOne
    @JoinColumn(name = "documentTypeId")
    private DocumentTypes documentTypes;

    @ManyToOne
    @JoinColumn(name = "menuCodeId")
    private MenuCodes menuCodes;

    @ManyToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schoolsEntity;
}
