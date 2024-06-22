package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SchoolTypeDTO {
    @NotBlank(message = "name is mandatory!")
    private String name;
}
