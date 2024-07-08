package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubjectDTO {
    @NotBlank(message = "This field is required!")
    private String subject;
    @NotNull(message = "levelId is required!")
    private int levelId;
}
