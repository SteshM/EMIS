package com.emis.EMIS.wrappers.requestDTOs;

import com.emis.EMIS.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class AuditTrailDTO {
    private String action;
    private String actionDescription;
    private String createdBy;
    private Instant deletedAt;
    private String deletedBy;
    private Status status;

}
