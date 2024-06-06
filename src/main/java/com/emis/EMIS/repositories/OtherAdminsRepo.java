package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.OtherAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtherAdminsRepo extends JpaRepository<OtherAdminEntity,Integer> {
    List<OtherAdminEntity> findByStatus(Status status);

    OtherAdminEntity findByAdminId(int adminId);
}
