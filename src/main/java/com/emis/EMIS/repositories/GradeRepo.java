package com.emis.EMIS.repositories;

import com.emis.EMIS.models.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepo extends JpaRepository<GradeEntity,Integer> {
}
