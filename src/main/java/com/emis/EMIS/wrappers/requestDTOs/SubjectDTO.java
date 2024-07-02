package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectDTO {
    @NotBlank(message = "This field is required!")
    private String subject;
    private int levelId;
}
