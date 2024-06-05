package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.SchoolAdminInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolAdminRepo  extends JpaRepository<SchoolAdminInfoEntity,Integer> {
    List<SchoolAdminInfoEntity> findByStatus(Status status);
}
