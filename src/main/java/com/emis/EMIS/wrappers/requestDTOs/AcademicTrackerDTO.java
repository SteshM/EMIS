package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AcademicTrackerDTO {
    private int schoolId;
    private int studentId;
    private int learningStageId;
    private int streamId;
    private int term;
    private int year;
}
