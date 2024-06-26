package com.emis.EMIS.services;

import com.emis.EMIS.models.CountyEntity;
import com.emis.EMIS.models.SubCountyEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.CountyDTO;
import com.emis.EMIS.wrappers.requestDTOs.SubCountyDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CountyService {
    private final ModelMapper modelMapper;
    private final DataService dataService;
    private final Utilities utilities;

    /**
     * COUNTY
     * @param countyDTO request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addCounty(CountyDTO countyDTO) throws JsonProcessingException {
        CountyEntity countyEntity = modelMapper.map(countyDTO,CountyEntity.class);
        log.info("About to save a county ::{}",new ObjectMapper().writeValueAsString(countyEntity));
        dataService.saveCounty(countyEntity);
        return utilities.successResponse("Added a county",countyDTO);
    }

    public ResponseDTO updateCounty(CountyDTO countyDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        CountyEntity countyEntity = dataService.findByCountyId(id);
        log.info("Fetched a county from the db:{}", objectMapper.writeValueAsString(countyEntity));
        modelMapper.map(countyEntity,countyDTO);
        log.info("Updated county Details. About to save:{}", objectMapper.writeValueAsString(countyEntity));
        dataService.saveCounty(countyEntity);
        return utilities.successResponse("updated county details successfully",countyDTO);
    }

    public ResponseDTO getAllCounties() throws JsonProcessingException {
        List<CountyEntity> countyEntityList = dataService.fetchAllCounties();
        List<CountyDTO>countyDTOList = countyEntityList.stream()
                .map(countyEntity -> {
                    return modelMapper.map(countyEntity,CountyDTO.class);
                })
                .toList();
        log.info("Fetched  all  counties Details:{}", new ObjectMapper().writeValueAsString(countyEntityList));
        return utilities.successResponse("fetched all counties",countyEntityList);

    }

    /**
     * SUB-COUNTY
     * @param subCountyDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addSubCounty(SubCountyDTO subCountyDTO) throws JsonProcessingException {
        SubCountyEntity subCounty =modelMapper.map(subCountyDTO, SubCountyEntity.class);
        log.info("About to save a subCounty:{}", new ObjectMapper().writeValueAsString(subCounty));
        dataService.saveSubCounty(subCounty);
        return utilities.successResponse("saved a subCounty",subCountyDTO);

    }
    public ResponseDTO updateSubCounty(SubCountyDTO subCountyDTO, int id) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var subCountyEntity = dataService.findBySubCountyId(id);
        log.info("Fetched a subCounty from the db:{}", objectMapper.writeValueAsString(subCountyEntity));
        modelMapper.map(subCountyEntity,subCountyDTO);
        log.info("Updated subCounty Details. About to save:{}", objectMapper.writeValueAsString(subCountyEntity));
        dataService.saveSubCounty(subCountyEntity);
        return utilities.successResponse("updated subCounty details successfully",subCountyDTO);
    }


    public ResponseDTO getAllSubCounties() throws JsonProcessingException {
        List<SubCountyEntity>subCountyEntityList = dataService.fetchAllSubCounties();
        List<SubCountyDTO>subCountyDTOList = subCountyEntityList.stream()
                .map(subCounty -> {
                    return modelMapper.map(subCounty, SubCountyDTO.class);
                })
                .toList();
        log.info("Fetched  all  subCounties :{}", new ObjectMapper().writeValueAsString(subCountyEntityList));
        return utilities.successResponse("Fetches all subCounties",subCountyDTOList);
    }



}
