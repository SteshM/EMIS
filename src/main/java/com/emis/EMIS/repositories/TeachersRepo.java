package com.emis.EMIS.repositories;

import com.emis.EMIS.models.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersRepo extends JpaRepository<TeacherEntity, Integer> {
}
