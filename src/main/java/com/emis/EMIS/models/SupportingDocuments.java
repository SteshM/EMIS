package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "supportingDocuments")
public class SupportingDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supportDocId;
    private String name;
    @Lob
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "document_types_id")
    private DocumentTypes documentTypes;
    @ManyToOne
    @JoinColumn(name = "menu_codes_id")
    private MenuCodes menuCodes;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolsEntity schoolsEntity;
    private int createdBy;
    private int deletedBy;
    private int updatedBy;


}
