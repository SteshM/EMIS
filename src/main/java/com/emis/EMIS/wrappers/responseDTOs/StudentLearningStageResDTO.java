package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentLearningStageResDTO {
    private int learningStageId;
    private String learningStage;
    private String registrationNo;
    private int studentId;

}
