package com.emis.EMIS.repositories;

import com.emis.EMIS.models.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepo extends JpaRepository<UserRoleEntity,Integer> {
    List<UserRoleEntity> findByUserId(int userId);
}
