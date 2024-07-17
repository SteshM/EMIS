package com.emis.EMIS.repositories;

import com.emis.EMIS.models.StudentMarksEntity;
import com.emis.EMIS.models.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentMarksRepo extends JpaRepository<StudentMarksEntity,Integer> {

    StudentMarksEntity findByMarksId(int marksId);

    List<StudentMarksEntity> findBySubject(SubjectEntity subject);
}
