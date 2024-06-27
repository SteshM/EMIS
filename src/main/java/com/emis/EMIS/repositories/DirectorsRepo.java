package com.emis.EMIS.repositories;

import com.emis.EMIS.models.DirectorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorsRepo extends JpaRepository<DirectorsEntity,Integer> {
    DirectorsEntity findByDirectorId(int directorId);
}
