package com.emis.EMIS.wrappers.requestDTOs;

import com.emis.EMIS.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Setter
@Getter
public class AuditTrailDTO {
    @NotNull(message = "action cannot be null")
    private String action;
    @NotNull(message = "actionDescription cannot be null")
    private String actionDescription;
    @NotNull(message = "isSuccessful cannot be null")
    private boolean isSuccessful;

}
