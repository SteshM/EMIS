package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class countyDTO {
    @NotBlank(message = "This field is required!")
    private String county;
}
