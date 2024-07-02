package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "grades")
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;
    private String grade;
    private Date dateCreated = new Date();
    private String createdBy;
    private Date dateModified =new Date();
    private String modifiedBy;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "levelsId")
    private LevelsEntity levelsEntity;
}
