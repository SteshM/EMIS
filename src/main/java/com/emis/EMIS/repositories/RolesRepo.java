package com.emis.EMIS.repositories;

import com.emis.EMIS.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepo extends JpaRepository<RolesEntity,Integer> {

    RolesEntity findByRoleId(int roleId);
    List<RolesEntity> findByProfileId(int profileId);
}
