package com.emis.EMIS.repositories;

import com.emis.EMIS.models.SchoolAdminInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolAdminRepo  extends JpaRepository<SchoolAdminInfoEntity,Integer> {
}
