package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "schoolContacts")
public class SchoolContacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolContactId;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "designationId")
    private DesignationEntity designationEntity;

    @ManyToOne
    private SchoolsEntity schoolsEntity ;
    private int createdBy;
    private int updatedBy;


//    @ManyToOne
//    @JoinColumn(name = "document_types_id")
//    private DocumentTypes documentTypes;
//
//    @ManyToOne
//    @JoinColumn(name = "menu_codes_id")
//    private MenuCodes menuCodes;
}
