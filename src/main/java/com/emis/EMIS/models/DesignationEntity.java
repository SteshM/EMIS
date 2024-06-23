package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="designation")
public class DesignationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int designationId;
    private String designation;
}
