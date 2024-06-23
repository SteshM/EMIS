package com.emis.EMIS.repositories;

import com.emis.EMIS.models.DioceseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DioceseRepo  extends JpaRepository<DioceseEntity,Integer> {
    DioceseEntity findByDioceseId(int dioceseId);
}
