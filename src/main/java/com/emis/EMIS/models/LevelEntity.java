package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "levels")

public class LevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int levelId;
    private String levelName;

}
