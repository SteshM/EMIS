package com.emis.EMIS.wrappers.responseDTOs;

import com.emis.EMIS.models.SubjectEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TeacherSubjectResDTO {
    private int teacherId;
    List<SubjectEntity> subjectEntityList;
}
