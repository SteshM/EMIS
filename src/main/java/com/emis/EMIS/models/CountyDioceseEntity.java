package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "countyDiocese")
public class CountyDioceseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countyDioceseId;

    @ManyToOne
    @JoinColumn(name = "county_id")
    private CountyEntity countyEntity;

    @ManyToOne
    @JoinColumn(name = "diocese_id")
    private DioceseEntity dioceseEntity;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    private String modifiedBy;
}
