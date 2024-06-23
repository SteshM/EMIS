package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diocese")
public class DioceseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dioceseId;
    private String diocese;
}
