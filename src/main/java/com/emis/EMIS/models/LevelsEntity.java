package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "levels")
public class LevelsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int levelId;
    private String levelName;

    private Date dateCreated ;
    private String createdBy;
    private Date dateModified;
    private String modifiedBy;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "curriculumId")
    private CurriculumEntity curriculum;

    @OneToMany
    List<LearningStageEntity>learningStageEntityList;
}