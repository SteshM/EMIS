package com.emis.EMIS.repositories;

import com.emis.EMIS.models.UniversalDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversalDocumentRepo extends JpaRepository<UniversalDocumentEntity,Integer> {
}
