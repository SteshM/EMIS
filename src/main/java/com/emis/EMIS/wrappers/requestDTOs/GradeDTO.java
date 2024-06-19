package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GradeDTO {
    @NotBlank(message = "Grade is required!")
    private String grade;
    @NotNull(message = "This field cannot be null!")
    private String stream;
}
