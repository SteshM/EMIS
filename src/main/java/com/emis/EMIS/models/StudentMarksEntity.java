package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "studentMarks")
public class StudentMarksEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int marksId;
    private double mark;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private StudentEntity student ;


}
