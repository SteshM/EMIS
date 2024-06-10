package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolAdminService;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/schAdmin")
public class SchoolAdminController {

private SchoolAdminService schoolAdminService;

@GetMapping("/students")
    public ResponseDTO viewStudents(){
    return schoolAdminService.viewStudents();
}
@GetMapping("/student")
    public ResponseDTO  fetchOne(@PathVariable int id){
    return schoolAdminService.fetchOne(id);
}
@PutMapping("/update-student/{id}")
public ResponseDTO updateStudentDetails(@PathVariable int id, @RequestBody StudentDTO studentDTO){
    return schoolAdminService.updateStudent(id,studentDTO);
}
@DeleteMapping("/del-student/{id}")
    public ResponseDTO softDelete(@PathVariable int id){
    return schoolAdminService.deleteStudent(id);
}
      }
