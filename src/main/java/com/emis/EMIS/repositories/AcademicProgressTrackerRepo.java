package com.emis.EMIS.repositories;

import com.emis.EMIS.models.AcademicProgressTrackerEntity;
import com.emis.EMIS.models.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  AcademicProgressTrackerRepo extends JpaRepository<AcademicProgressTrackerEntity,Integer> {
    @Query(nativeQuery = true, value = "SELECT DISTINCT student_id FROM academic_progress_tracker")
    List<AcademicProgressTrackerEntity> findDistinctStudentId();
}
