package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.SystemAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtherAdminsRepo extends JpaRepository<SystemAdminEntity,Integer> {
    List<SystemAdminEntity> findByStatus(Status status);

    SystemAdminEntity findByAdminId(int adminId);
}
