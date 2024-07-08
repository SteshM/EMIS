package com.emis.EMIS.repositories;

import com.emis.EMIS.models.SchoolsEntity;
import com.emis.EMIS.models.StreamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreamRepo extends JpaRepository<StreamsEntity,Integer> {
    StreamsEntity findByStreamId(int streamId);
    List<StreamsEntity> findBySchoolsEntity(SchoolsEntity schools);
}
