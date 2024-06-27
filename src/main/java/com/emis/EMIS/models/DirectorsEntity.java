package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="directorsEntity")
public class DirectorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int directorId;

}
