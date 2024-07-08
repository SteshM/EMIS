package com.emis.EMIS.repositories;

import com.emis.EMIS.models.CurriculumEntity;
import com.emis.EMIS.models.LevelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelsRepo  extends JpaRepository<LevelsEntity,Integer> {
    LevelsEntity findByLevelId(int levelId);
    List<LevelsEntity> findByCurriculum(CurriculumEntity curriculum);
}
