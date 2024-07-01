package com.emis.EMIS.repositories;

import com.emis.EMIS.models.DirectorsDocsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorsDocsRepo extends JpaRepository<DirectorsDocsEntity,Integer> {
    DirectorsDocsEntity findByDirectorDocId(int directorId);
}
