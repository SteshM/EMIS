package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DesignationDTO {
    @NotBlank(message = "This field is required!")
    private String designation;
}
