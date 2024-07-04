package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "learningStages")
public class LearningStageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int learningStageId;
    private String learningStage;
    private int year;
    private String stream;
    private Status status;
    private Date dateCreated  = new Date();
    private String createdBy;
    private Date dateModified =new Date();
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "levelId")
    private LevelsEntity levelsEntity;

    @OneToMany
    List<StudentEntity>studentEntityList;
}
