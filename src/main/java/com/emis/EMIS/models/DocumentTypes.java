package com.emis.EMIS.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "documentTypes")
public class DocumentTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentTypeId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "menu_codes_id")
    private MenuCodes menuCodes;

}
