package com.emis.EMIS.repositories;

import com.emis.EMIS.models.PartnerInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepo extends JpaRepository<PartnerInfoEntity,Integer> {
}
