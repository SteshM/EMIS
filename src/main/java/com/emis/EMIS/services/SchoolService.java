package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.SchoolsEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.SchoolDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {
 private final ModelMapper modelMapper;
 private final DataService dataService;
 private final Utilities utilities;

    /**
     * Enrolling a school
     * @param schoolDTO the school dto
     * @return response dto
     */

    public ResponseDTO createBasicInfo(SchoolDTO schoolDTO) throws JsonProcessingException {
        var school = modelMapper.map(schoolDTO,SchoolsEntity.class);
        log.info("About to save a school's basic info:{}", new ObjectMapper().writeValueAsString(school));
        dataService.saveSchool(school);
        return utilities.successResponse("Created school",schoolDTO);
    }

    /**
     * A method to view all active schools in the system
     * @return response dto
     */

    public ResponseDTO viewSchools() throws JsonProcessingException {
        List<SchoolsEntity>schoolsEntities = dataService.findAll();
        log.info("About to fetch schools {}",schoolsEntities);
        List<SchoolDTO>schoolDTOList = schoolsEntities.stream()
                .map(schools -> {
                    return modelMapper.map(schools, SchoolDTO.class);
                })
                .toList();
        log.info("Fetched  all school Details:{}", new ObjectMapper().writeValueAsString(schoolsEntities));
        return utilities.successResponse("Successfully fetched schools",schoolDTOList);

    }

    /**
     * Fetching a school, updating its details and saving
     * @param id school id
     * @param schoolDTO the school dto
     * @return response dto
     */

    public ResponseDTO updateSchool(int id, SchoolDTO schoolDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var school = dataService.findBySchoolId(id);
        log.info("Fetched a school:{}", objectMapper.writeValueAsString(school));
        modelMapper.map(schoolDTO,school);
        log.info("Updated school Details. About to save:{}", objectMapper.writeValueAsString(school));
        dataService.saveSchool(school);
        return utilities.successResponse("Successfully updated school details",schoolDTO);
    }

    /**
     * soft deleting a school
     * @param id schoolId
     * @return response dto
     */

    public ResponseDTO deleteSchool(int id) {
        var school = dataService.findBySchoolId(id);
        school.setStatus(Status.DELETED);
        dataService.saveSchool(school);
        return utilities.successResponse("deleted school",null);
    }
}
