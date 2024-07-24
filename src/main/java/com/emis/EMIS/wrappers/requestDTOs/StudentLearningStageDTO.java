package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentLearningStageDTO {
    @NotNull(message = "studentId is required")
    private int studentId;
    @NotNull(message = "learningStageId is required")
    private int learningStageId;
}
