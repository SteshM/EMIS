package com.emis.EMIS.wrappers.requestDTOs;

import com.emis.EMIS.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Setter
@Getter
public class AuditTrailDTO {
    private String action;
    private String actionDescription;
    private boolean isSuccessful;

}
