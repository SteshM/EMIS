package com.emis.EMIS.repositories;

import com.emis.EMIS.models.EducationalResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationalResourceRepo extends JpaRepository<EducationalResourceEntity,Integer> {
    EducationalResourceEntity findByResourceId(int resourceId);
}
