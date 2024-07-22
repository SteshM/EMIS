package com.emis.EMIS.repositories;

import com.emis.EMIS.models.MenuCodes;
import com.emis.EMIS.models.SchoolMenuCodeStatuses;
import com.emis.EMIS.models.SchoolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolMenuCodeStatusRepo  extends JpaRepository<SchoolMenuCodeStatuses,Integer> {

   SchoolMenuCodeStatuses findBySchoolsEntityAndMenuCodes(SchoolsEntity schoolEntity , MenuCodes menuCodes);


//   Optional<SchoolMenuCodeStatuses> findBySchoolsEntitySchoolIdAndMenuCodesMenuCodeIdAndRemarksClarificationStatus(int schoolId, int menuCodeId, String open);

   Optional<SchoolMenuCodeStatuses> findBySchoolsEntitySchoolIdAndMenuCodesMenuCodeId(int schoolId, int menuCodeId);

   List<SchoolMenuCodeStatuses> findAllBySchoolsEntitySchoolId(int schoolId);

    SchoolMenuCodeStatuses findBySchoolMenuCodeStatusId(int schoolMenuCodeStatusId);
}
