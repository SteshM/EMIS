package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MarksResDTO {
    private int marksId;
    private double marks;
    private int studentId;
    private int subjectId;


}
