package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
@Table(name = "levels")
public class LevelsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int levelId;
    private String levelName;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "curriculumId")
    private CurriculumEntity curriculum;

    @OneToMany
    @JsonManagedReference
    List<LearningStageEntity>learningStageEntityList;

    @OneToMany()
    List<SubjectEntity>subjectEntityList;
}
