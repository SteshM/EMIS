package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "streams")
public class StreamsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int streamId;
    private String stream;
    private Status status;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated =new Date();
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    private String modifiedBy;

    @ManyToOne()
    @JoinColumn(name = "schoolId")
    private SchoolsEntity schoolsEntity;


}
