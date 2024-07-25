package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApproveSchoolDTO {
    @NotNull(message = "schoolId cannot be null")
    private int schoolId;
//    @NotNull(message = "name cannot be null")
//    private String name;
//    @NotNull(message = "remarks cannot be null")
    private String remarks;
}
