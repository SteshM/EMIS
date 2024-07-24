package com.emis.EMIS.wrappers.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SubjectsTeacherDTO {
    @NotNull(message = "teacherId is required")
    private int teacherId;
    @NotNull(message = "subjectId is required")
    private List<Integer> subjectIds;

}
