package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "schoolDocuments")
public class SchoolDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolDocId;

    @ManyToOne
    @JoinColumn(name = "documentTypeId")
    private DocumentTypes documentTypes;

    @ManyToOne
    @JoinColumn(name = "menuCodeId")
    private MenuCodes menuCodes;

    @ManyToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schoolsEntity;

    @ManyToOne
    @JoinColumn(name = "supportingId")
    private SupportingDocuments supportingDocuments;

    private String docName;
    private String docUrl;
    private String docSize;
    private String docType;
    private String docKey;

}
