package com.emis.EMIS.repositories;

import com.emis.EMIS.models.GuardianEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepo extends JpaRepository<GuardianEntity,Integer> {
}
