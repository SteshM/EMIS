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
    private int academicProgressTrackerId;
    private int schoolId;
    private int studentId;
    private int learningStageId;
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
