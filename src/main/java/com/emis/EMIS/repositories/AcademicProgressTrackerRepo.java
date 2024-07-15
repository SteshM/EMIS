package com.emis.EMIS.repositories;

import com.emis.EMIS.models.AcademicProgressTrackerEntity;
import com.emis.EMIS.models.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  AcademicProgressTrackerRepo extends JpaRepository<AcademicProgressTrackerEntity,Integer> {
//    List<AcademicProgressTrackerEntity> findDistinctStudentId();
}
