package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grades")
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;
    private String grade;
    private String stream;

    @ManyToOne
    @JoinColumn(name = "levelId")
    private LevelEntity level;
}
