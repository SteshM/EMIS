package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LearningStagesDTO {
    @NotNull(message = "levelId is required")
    private int levelId;
    @NotNull(message = "learningStage is required")
    private String learningStage;
    @NotNull(message = "year is required")
    private int year;
    @NotNull(message = "stream is required")
    private String stream;
}
