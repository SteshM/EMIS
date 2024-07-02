package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "curriculum")
public class CurriculumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int curriculumId;
    private String curriculum;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    private String modifiedBy;

}
