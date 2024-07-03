package com.emis.EMIS.repositories;

import com.emis.EMIS.models.LevelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelsRepo  extends JpaRepository<LevelsEntity,Integer> {
    LevelsEntity findByLevelId(int levelId);

}
