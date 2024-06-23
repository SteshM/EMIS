package com.emis.EMIS.repositories;

import com.emis.EMIS.models.CountyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepo extends JpaRepository<CountyEntity,Integer> {
    CountyEntity findByCountyId(int countyId);
}
