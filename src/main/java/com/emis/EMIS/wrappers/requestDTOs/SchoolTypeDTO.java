package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SchoolTypeDTO {
    @NotNull(message = "schoolTypeId cannot be null")
    private int schoolTypeId;

    @NotNull(message = "name cannot be null")
    private String name;
}
