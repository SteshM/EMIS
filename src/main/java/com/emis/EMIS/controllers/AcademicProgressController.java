package com.emis.EMIS.controllers;

import com.emis.EMIS.services.AcademicProgressTrackerService;
import com.emis.EMIS.services.DataService;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.AcademicTrackerDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class AcademicProgressController {

private final AcademicProgressTrackerService academicProgressTrackerService;

@GetMapping("/promote-students")
public ResponseDTO promoteStudentToNextTerm(){
    return academicProgressTrackerService.PromoteStudents();
}

}
