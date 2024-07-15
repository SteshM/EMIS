package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MarksDTO {
    private int subjectId;
    private int studentId;
    private double mark;
}
