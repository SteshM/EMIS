package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CurriculumDTO {
    @NotBlank(message = "This field is required!")
    private String curriculum;
}
