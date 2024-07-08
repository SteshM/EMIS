package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "role-profile")
public class RoleProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleProfileId;
    @ManyToOne
    @JoinColumn(name = "profileId")
    private ProfileEntity profileEntity;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private RolesEntity rolesEntity;
}
