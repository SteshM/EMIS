package com.emis.EMIS.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private int mark;
    private int term;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "subjectId")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private StudentEntity student ;


}
