package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "academicProgressTracker")
public class AcademicProgressTrackerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academic_progress_tracker_id")
    private int academicProgressTrackerId;
    @Column(name = "school_id")
    private int schoolId;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "learning_stage")
    private int learningStageId;
    @Column(name = "stream_id")
    private int streamId;
    private int term;
    private int year;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date() ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified ;
    private String modifiedBy;




}
