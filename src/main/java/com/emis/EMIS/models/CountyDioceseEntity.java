package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
