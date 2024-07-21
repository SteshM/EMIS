package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "menuCodes")
public class MenuCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCodeId;
    private String name;
    private String remarks;
    private boolean required = true;
    @Column(name = "records_required")
    private int recordsRequired;
    private float completionPercentage;
    private String createdBy;
    private String  updatedBy;
    private Status status;

}
