package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachersRepo extends JpaRepository<TeacherEntity, Integer> {
    List<TeacherEntity> findByStatus(Status status);

    TeacherEntity findByTeacherId(int teacherId);
}
