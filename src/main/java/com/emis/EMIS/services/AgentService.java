package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.SchoolsEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
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
        var school = modelMapper.map(schoolDTO,SchoolsEntity.class);
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

    public ResponseDTO updateSchool(int id, SchoolDTO schoolDTO) {
        var school = dataService.findBySchoolId(id);
        school.setSchoolName(schoolDTO.getSchoolName());
        school.setSchoolType(schoolDTO.getSchoolType());
        school.setCounty(schoolDTO.getCounty());
        school.setLocation(schoolDTO.getLocation());
        school.setEmailAddress(schoolDTO.getEmailAddress());
        school.setPostalAddress(schoolDTO.getPostalAddress());
        school.setPostalCode(schoolDTO.getPostalCode());
        school.setContact(schoolDTO.getContact());
        school.setMoeRegistrationNo(schoolDTO.getMoeRegistrationNo());
        SchoolDTO schoolDTO1= modelMapper.map(school, SchoolDTO.class);
        log.info("About to fetch school details and modify : {} ",schoolDTO1);
        dataService.saveSchool(school);
        return utilities.successResponse("Successfully updated school details",school);
    }

    public ResponseDTO deleteSchool(int id) {
        var school = dataService.findBySchoolId(id);
        school.setStatus(Status.DELETED);
        dataService.saveSchool(school);
        return utilities.successResponse("deleted school",null);
    }
}
