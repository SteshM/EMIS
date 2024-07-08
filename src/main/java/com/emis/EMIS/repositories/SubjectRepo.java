package com.emis.EMIS.repositories;

import com.emis.EMIS.models.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<SubjectEntity,Integer> {
    SubjectEntity findBySubjectId(int subjectId);

}
