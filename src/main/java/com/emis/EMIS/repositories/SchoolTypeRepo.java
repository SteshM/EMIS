package com.emis.EMIS.repositories;

import com.emis.EMIS.models.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolTypeRepo extends JpaRepository<SchoolType,Integer> {


    SchoolType findBySchoolTypeId(int schoolTypeId);
}
