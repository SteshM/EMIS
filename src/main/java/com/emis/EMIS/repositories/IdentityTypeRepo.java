package com.emis.EMIS.repositories;

import com.emis.EMIS.models.IdentityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityTypeRepo extends JpaRepository<IdentityType,Integer> {
    IdentityType findByIdentityTypeId(int identityTypeId);
}
