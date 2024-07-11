package com.emis.EMIS.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "studentMarks")
public class StudentMarksEntity {
    private int marksId;
    private Double mark;

    @OneToOne
    @JoinColumn(name = "subjectId")
    private SubjectEntity subject;


}
