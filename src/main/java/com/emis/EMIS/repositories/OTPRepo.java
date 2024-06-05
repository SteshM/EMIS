package com.emis.EMIS.repositories;

import com.emis.EMIS.models.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OTPRepo extends JpaRepository<OTPEntity, Integer> {

    @Query(nativeQuery = true, value = "select * FROM otp WHERE user_id = ?")
    OTPEntity findByUserId(int userId);

}
