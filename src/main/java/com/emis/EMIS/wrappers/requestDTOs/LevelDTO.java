package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LevelDTO {
    @NotBlank(message = "This field is required!")
    private int curriculumId;
    private String levelName;
}
