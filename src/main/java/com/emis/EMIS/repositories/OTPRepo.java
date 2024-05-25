package com.emis.EMIS.repositories;

import com.emis.EMIS.models.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepo extends JpaRepository<OTPEntity, Integer> {
}
