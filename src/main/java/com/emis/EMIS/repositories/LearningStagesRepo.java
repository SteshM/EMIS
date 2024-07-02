package com.emis.EMIS.repositories;

import com.emis.EMIS.models.LearningStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningStagesRepo extends JpaRepository<LearningStageEntity,Integer> {
}
