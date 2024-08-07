package com.emis.EMIS.repositories;

import com.emis.EMIS.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    UserEntity findByUserId(int userId);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.profiles")
    List<UserEntity> findAllUsersWithProfiles();

}
