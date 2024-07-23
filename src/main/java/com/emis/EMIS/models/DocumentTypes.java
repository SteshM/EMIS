package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "documentTypes")
public class DocumentTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentTypeId;
    private String docName;
    private String docUrl;
    private String docSize;
    private String docType;
    private Status status;
    private Date dateCreated = new Date() ;
    private String createdBy;
    private Date dateModified;
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "menuCodeId")
    private MenuCodes menuCodes;


    @ManyToOne
    @JoinColumn(name = "schoolId")
    private SchoolsEntity school;



}
