package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "subCounty")
public class SubCountyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCountyId;
    private String subCounty;

    @ManyToOne
    @JoinColumn(name = "countyId")
    private CountyEntity countyEntity;

}
