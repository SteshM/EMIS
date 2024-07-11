package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "studentMarks")
public class StudentMarksEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int marksId;
    private Double mark;

    @OneToOne
    @JoinColumn(name = "subjectId")
    private SubjectEntity subject;


}
