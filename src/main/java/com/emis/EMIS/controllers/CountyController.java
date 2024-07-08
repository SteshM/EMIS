package com.emis.EMIS.controllers;
import com.emis.EMIS.services.CountyService;
import com.emis.EMIS.services.SchoolService;
import com.emis.EMIS.wrappers.requestDTOs.CountyDTO;
import com.emis.EMIS.wrappers.requestDTOs.SubCountyDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1")
@RestController
@Slf4j
@RequiredArgsConstructor
public class CountyController {
    private final CountyService countyService;

    /**
     * COUNTY
     * @param countyDTO the dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/county")
    public ResponseDTO addCounty(@Valid @RequestBody  CountyDTO countyDTO) throws JsonProcessingException {
        return countyService.addCounty(countyDTO);
    }
    @PutMapping("/county/{id}")
    public ResponseDTO updateCounty(@RequestBody CountyDTO countyDTO,@PathVariable int id) throws JsonProcessingException {
        return countyService.updateCounty(countyDTO,id);
    }
    @GetMapping("/counties")
    public ResponseDTO getCounties() throws JsonProcessingException {
        return countyService.getAllCounties();
    }

    /**
     * SUB-COUNTY
     * @param subCountyDTO the request dto
     * @return the response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/county/{id}/sub-county")
    public ResponseDTO addSubCounty(@Valid @RequestBody SubCountyDTO subCountyDTO) throws JsonProcessingException {
        return  countyService.addSubCounty(subCountyDTO);
    }
    @PutMapping("/sub-county/{id}")
    public ResponseDTO updateSubCounty(@RequestBody SubCountyDTO subCountyDTO,@PathVariable int id) throws JsonProcessingException {
        return countyService.updateSubCounty(subCountyDTO,id);}

    @GetMapping("/sub-county")
    public ResponseDTO getSubCounties() throws JsonProcessingException {
        return countyService.getAllSubCounties();
    }

}
