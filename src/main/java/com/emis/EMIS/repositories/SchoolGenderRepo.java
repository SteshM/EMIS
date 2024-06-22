package com.emis.EMIS.repositories;

import com.emis.EMIS.models.SchoolGender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolGenderRepo extends JpaRepository<SchoolGender,Integer> {

    SchoolGender findBySchoolGenderId(int schoolGenderId);
}
