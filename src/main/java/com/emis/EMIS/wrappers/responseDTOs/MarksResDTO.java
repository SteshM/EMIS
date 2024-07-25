package com.emis.EMIS.wrappers.responseDTOs;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarksResDTO {
    private int marksId;
    private int mark;
    private int studentId;
    private int subjectId;
    private int term;
    private String subject;


}
