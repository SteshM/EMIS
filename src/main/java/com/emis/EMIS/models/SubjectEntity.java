package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "subjects")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;
    private String subject;
    private Date dateCreated  = new Date();
    private String createdBy;
    private Date dateModified =new Date();
    private String modifiedBy;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "levelId")
    private LevelsEntity levels;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private TeacherEntity teacher;

    @OneToMany
    @JoinColumn(name = "marksId")
    List<StudentMarksEntity> studentMarks;



}
