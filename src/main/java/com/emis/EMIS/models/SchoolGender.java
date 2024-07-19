package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "schoolGender")
public class SchoolGender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolGenderId;
    private String name;
}
