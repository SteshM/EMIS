package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "academicProgressTracker")
public class AcademicProgressTrackerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int academicProgressTrackerId;
    private int schoolId;
    private int studentId;
    private int learningStageId;
    private int streamId;
    private int term;
    private int year;


}
