package com.emis.EMIS.models;

import com.emis.EMIS.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "auditTrail")
public class AuditTrailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auditTrailId;

    @Column(name = "action", nullable = false)
    private String action;
    @Lob
    @Column(name = "action_description", columnDefinition = "TEXT", nullable = false)
    private String actionDescription;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "status")
    private Status status;
}
