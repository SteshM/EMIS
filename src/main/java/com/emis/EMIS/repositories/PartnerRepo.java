package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.PartnerInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepo extends JpaRepository<PartnerInfoEntity,Integer> {
    Page<PartnerInfoEntity> findByStatus(Status status, Pageable pageable);

    PartnerInfoEntity findByPartnerId(int partnerId);
}
