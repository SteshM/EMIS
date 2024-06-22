package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "curriculum")
public class CurriculumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int curriculumId;
    private String curriculum;


}
