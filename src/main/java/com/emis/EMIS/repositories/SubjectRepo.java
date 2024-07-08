package com.emis.EMIS.repositories;

import com.emis.EMIS.models.LevelsEntity;
import com.emis.EMIS.models.SubjectEntity;
import com.emis.EMIS.models.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<SubjectEntity,Integer> {
    SubjectEntity findBySubjectId(int subjectId);
    List<SubjectEntity> findByLevels(LevelsEntity levels);

    List<SubjectEntity> findByTeacher(TeacherEntity teacher);
}
