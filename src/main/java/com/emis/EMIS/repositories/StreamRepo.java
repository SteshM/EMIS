package com.emis.EMIS.repositories;

import com.emis.EMIS.models.StreamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamRepo extends JpaRepository<StreamsEntity,Integer> {
    StreamsEntity findByStreamId(int streamId);
}
