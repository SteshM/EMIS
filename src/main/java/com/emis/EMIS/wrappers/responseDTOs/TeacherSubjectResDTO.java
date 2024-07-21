package com.emis.EMIS.wrappers.responseDTOs;

import com.emis.EMIS.models.SubjectEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class TeacherSubjectResDTO {
    private int teacherId;
    List<SubjectTeacherDTO>subjectTeacherDTOList;

}
