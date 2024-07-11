package com.emis.EMIS.repositories;

import com.emis.EMIS.models.AcademicProgressTrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AcademicProgressTrackerRepo extends JpaRepository<AcademicProgressTrackerEntity,Integer> {
}
