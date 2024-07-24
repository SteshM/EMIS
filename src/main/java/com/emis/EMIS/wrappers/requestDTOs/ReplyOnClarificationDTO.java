package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReplyOnClarificationDTO {
    @NotNull(message = "schoolId is required")
    private int schoolId;
    @NotNull(message = "menuCodeId is required")
    private int menuCodeId;
    @NotNull(message = "remarks is required")
    private String remarks;
}
