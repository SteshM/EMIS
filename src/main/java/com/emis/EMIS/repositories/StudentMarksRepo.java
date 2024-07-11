package com.emis.EMIS.repositories;

import com.emis.EMIS.models.StudentMarksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentMarksRepo extends JpaRepository<StudentMarksEntity,Integer> {
}
