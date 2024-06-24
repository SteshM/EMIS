package com.emis.EMIS.wrappers.requestDTOs;

import com.emis.EMIS.models.SchoolMenuCodeStatuses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolMenuCodeStatusRepo  extends JpaRepository<SchoolMenuCodeStatuses,Integer> {
}
