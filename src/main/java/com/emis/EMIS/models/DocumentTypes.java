package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
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
    private Status status;

    @ManyToOne
    @JoinColumn(name = "menuCodeId")
    private MenuCodes menuCodes;




}
