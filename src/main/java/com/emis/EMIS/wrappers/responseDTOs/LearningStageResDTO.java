package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LearningStageResDTO {
    private int learningStageId;
    private String learningStage;
    private int year;
    private String stream;
}
