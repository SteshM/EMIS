package com.emis.EMIS.repositories;

import com.emis.EMIS.models.CurriculumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepo  extends JpaRepository<CurriculumEntity,Integer> {
}
