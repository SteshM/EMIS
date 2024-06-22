package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.SchoolGender;
import com.emis.EMIS.models.SchoolType;
import com.emis.EMIS.models.SchoolsEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.SchoolGenderDTO;
import com.emis.EMIS.wrappers.requestDTOs.SchoolTypeDTO;
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


    /**
     * CRUD
     * SCHOOL TYPES
     * @param schoolTypeDTO school type dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addSchoolType(SchoolTypeDTO schoolTypeDTO) throws JsonProcessingException {
        var schoolType = modelMapper.map(schoolTypeDTO,SchoolType.class);
        log.info("About to save a schoolType basic info:{}", new ObjectMapper().writeValueAsString(schoolType));
        dataService.saveSchoolType(schoolType);
        return utilities.successResponse("added a school type",schoolTypeDTO);


    }

    public ResponseDTO getAllSchoolTypes() throws JsonProcessingException {
        List<SchoolType>schoolTypeList = dataService.fetchSchoolTypes();
        log.info("About to fetch all school types from the db::{}",schoolTypeList);
        List<SchoolTypeDTO>schoolTypeDTOList = schoolTypeList.stream()
                .map(schoolType -> {
                    return modelMapper.map(schoolType,SchoolTypeDTO.class);
                })
                .toList();
        log.info("Fetched  all  schoolType Details:{}", new ObjectMapper().writeValueAsString(schoolTypeList));
return utilities.successResponse("fetched all school types",schoolTypeDTOList);


    }

    public ResponseDTO updateSchoolType(int id, SchoolTypeDTO schoolTypeDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        SchoolType schoolType = dataService.findBySchoolTypeId(id);
        log.info("Fetched a school type:{}", objectMapper.writeValueAsString(schoolType));
        modelMapper.map(schoolTypeDTO,schoolType);
        log.info("Updated school type Details. About to save:{}", objectMapper.writeValueAsString(schoolType));
        return utilities.successResponse("updated school type successfully",schoolTypeDTO);


    }

    /**
     * CRUD
     * SCHOOL GENDER
     * @param schoolGenderDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addSchoolGender(SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        var schoolGender = modelMapper.map(schoolGenderDTO,SchoolGender.class);
        log.info("About to save a schoolGender basic info:{}", new ObjectMapper().writeValueAsString(schoolGender));
        dataService.saveSchoolGender(schoolGender);
        return utilities.successResponse("added a school type",schoolGenderDTO);
    }

    public ResponseDTO getAllSchoolGenders() throws JsonProcessingException {
        List<SchoolGender>schoolGenderList = dataService.fetchSchoolGenders();
        log.info("About to fetch all school genders from the db::{}",schoolGenderList);
        List<SchoolGenderDTO>schoolGenderDTOList = schoolGenderList.stream()
                .map(schoolGender -> {
                    return modelMapper.map(schoolGender,SchoolGenderDTO.class);
                })
                .toList();
        log.info("Fetched  all  school gender Details:{}", new ObjectMapper().writeValueAsString(schoolGenderList));
        return utilities.successResponse("fetched all school types",schoolGenderDTOList);
    }

    public ResponseDTO updateSchoolGender(int id, SchoolGenderDTO schoolGenderDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        SchoolGender schoolGender = dataService.findBySchoolGenderId(id);
        log.info("Fetched a school gender:{}", objectMapper.writeValueAsString(schoolGender));
        modelMapper.map(schoolGender,schoolGenderDTO);
        //not saving new record
        log.info("Updated school gender Details. About to save:{}", objectMapper.writeValueAsString(schoolGender));
        return utilities.successResponse("updated school type successfully",schoolGenderDTO);
    }




}
