package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "county")
public class CountyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countyId;
    private String county;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countyEntity")
    private List<SubCountyEntity>subCountyEntityList;
}
