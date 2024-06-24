package com.emis.EMIS.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "identityType")
public class IdentityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identityTypeId;


    @Size(max = 30)
    @Column(name = "code", length = 30)
    private String code;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;
}
