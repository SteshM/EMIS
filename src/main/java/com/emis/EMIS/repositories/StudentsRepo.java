package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentsRepo extends JpaRepository<StudentEntity,Integer> {
    StudentEntity findByStudentId(int studentId);

    List<StudentEntity> findByStatus(Status status);

}
