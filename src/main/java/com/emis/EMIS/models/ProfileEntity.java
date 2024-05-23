package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
    private String profileName;


}
