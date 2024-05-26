package com.emis.EMIS.repositories;

import com.emis.EMIS.models.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepo extends JpaRepository<OTPEntity, Integer> {

    Optional<OTPEntity> findByUserEntityUserId(int userId);

}
