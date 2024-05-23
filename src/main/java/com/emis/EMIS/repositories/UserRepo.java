package com.emis.EMIS.repositories;

import com.emis.EMIS.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    Map<Object, Object> findByName(String userName);
}
