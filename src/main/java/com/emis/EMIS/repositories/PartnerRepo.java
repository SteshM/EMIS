package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.PartnerInfoEntity;
import com.emis.EMIS.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepo extends JpaRepository<PartnerInfoEntity,Integer> {
    List<PartnerInfoEntity> findByStatus(Status status);

    PartnerInfoEntity findByPartnerId(int partnerId);

    PartnerInfoEntity findByUserEntity(UserEntity userEntity);
}
