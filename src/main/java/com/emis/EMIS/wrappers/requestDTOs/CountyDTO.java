package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountyDTO {
    @NotNull(message = "This field is required!")
    @Size(min = 2 ,message = "must have 2 or more characters")
    private String county;
}
