package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.SchoolsEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.SchoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentService {
 private final ModelMapper modelMapper;
 private final DataService dataService;
 private final Utilities utilities;

    public ResponseDTO enrolSchool(SchoolDTO schoolDTO) {
        SchoolsEntity school = modelMapper.map(schoolDTO,SchoolsEntity.class);
        school.setStatus(Status.PENDING);
        log.info("About to fetch the saved school {}",school);
        dataService.saveSchool(school);
        return utilities.successResponse("Created school",school);
    }

    public ResponseDTO viewSchools() {
        List<SchoolsEntity>schoolsEntities = dataService.findAll();
        log.info("About to fetch schools {}",schoolsEntities);
        return utilities.successResponse("Successfully fetched schools",schoolsEntities);

    }
    
}
