package com.emis.EMIS.repositories;

import com.emis.EMIS.models.MenuCodes;
import com.emis.EMIS.models.SchoolMenuCodeStatuses;
import com.emis.EMIS.models.SchoolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolMenuCodeStatusRepo  extends JpaRepository<SchoolMenuCodeStatuses,Integer> {

   SchoolMenuCodeStatuses findBySchoolsEntityAndMenuCodes(SchoolsEntity schoolEntity , MenuCodes menuCodes);
}
