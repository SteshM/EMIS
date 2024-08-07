package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherSubjectDTO {
    @NotNull(message = "teacherId is required")
    private int teacherId;
    @NotNull(message = "subjectId is required")
    private int subjectId;
}
