package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.GuardianEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianRepo extends JpaRepository<GuardianEntity,Long> {
    List<GuardianEntity> findByStatus(Status status);

    GuardianEntity findByGuardianId(int guardianId);
}
