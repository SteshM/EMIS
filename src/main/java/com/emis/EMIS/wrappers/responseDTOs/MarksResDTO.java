package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MarksResDTO {
    private int marksId;
    private double marks;
    private int studentId;
    private String student;
    private int subjectId;
    private String subject;


}
