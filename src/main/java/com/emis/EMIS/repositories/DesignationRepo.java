package com.emis.EMIS.repositories;

import com.emis.EMIS.models.DesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepo extends JpaRepository<DesignationEntity,Integer> {
    DesignationEntity findByDesignationId(int designationId);
}
