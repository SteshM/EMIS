package com.emis.EMIS.repositories;

import com.emis.EMIS.models.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepo extends JpaRepository<StudentEntity,Integer> {
}
