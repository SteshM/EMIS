package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AcademicTrackerDTO {
    @NotNull(message = "schoolId cannot be null")
    private int schoolId;
    @NotNull(message = "studentId cannot be null")
    private int studentId;
    @NotNull(message = "learningStageId cannot be null")
    private int learningStageId;
    @NotNull(message = "streamId cannot be null")
    private int streamId;
    @NotNull(message = "term cannot be null")
    private int term;
    @NotNull(message = "year cannot be null")
    private int year;
}
