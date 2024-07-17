package com.emis.EMIS.repositories;

import com.emis.EMIS.models.LearningStageEntity;
import com.emis.EMIS.models.LevelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LearningStagesRepo extends JpaRepository<LearningStageEntity,Integer> {
   Optional <LearningStageEntity> findByLearningStageId(int learningStageId);

    List<LearningStageEntity> findByLevelsEntity(LevelsEntity levels);
}
