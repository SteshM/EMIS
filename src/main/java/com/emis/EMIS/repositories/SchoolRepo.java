package com.emis.EMIS.repositories;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.SchoolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepo extends JpaRepository<SchoolsEntity,Integer> {
    SchoolsEntity findBySchoolId(int schoolId);
    
    SchoolsEntity findBySchoolIdAndStatus(int schoolId, Status status);

}
