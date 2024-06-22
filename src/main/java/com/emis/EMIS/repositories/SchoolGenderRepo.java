package com.emis.EMIS.repositories;

import com.emis.EMIS.models.SchoolGender;
import com.emis.EMIS.models.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolGenderRepo extends JpaRepository<SchoolGender,Integer> {
    SchoolType findBySchoolTypeId(int schoolTypeId);
}
