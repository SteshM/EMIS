package com.emis.EMIS.repositories;

import com.emis.EMIS.models.AuditTrailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditTrailRepo extends JpaRepository<AuditTrailEntity,Integer> {
}
