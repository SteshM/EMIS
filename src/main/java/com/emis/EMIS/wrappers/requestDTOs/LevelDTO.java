package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LevelDTO {
    @NotNull(message = "CurriculumId is required!")
    private int curriculumId;
    @NotNull(message = "levelName is required!")
    private String levelName;
}
