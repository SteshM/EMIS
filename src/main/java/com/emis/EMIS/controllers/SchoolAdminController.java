package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolAdminService;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.GuardianDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.StudentDTO;
import com.emis.EMIS.wrappers.responseDTOs.TeacherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/schAdmin")
public class SchoolAdminController {

private final SchoolAdminService schoolAdminService;

@GetMapping("/students")
    public ResponseDTO viewStudents() throws JsonProcessingException {
    return schoolAdminService.viewStudents();
}
@GetMapping("/student/{id}")
    public ResponseDTO  fetchOne(@PathVariable int id) throws JsonProcessingException {
    return schoolAdminService.fetchOne(id);
}
@PutMapping("/update-student/{id}")
public ResponseDTO updateStudentDetails(@PathVariable int id, @RequestBody StudentDTO studentDTO) throws JsonProcessingException {
    return schoolAdminService.updateStudent(id,studentDTO);
}
@DeleteMapping("/del-student/{id}")
    public ResponseDTO softDelete(@PathVariable int id){
    return schoolAdminService.deleteStudent(id);
}
@GetMapping("/teachers")
    public ResponseDTO viewActiveTeachers() throws JsonProcessingException {
    return schoolAdminService.viewTeachers();
}
@GetMapping("/teacher/{id}")
    public ResponseDTO fetchATeacher(@PathVariable int id) throws JsonProcessingException {
    return schoolAdminService.fetchTeacher(id);
}
@PutMapping("/teacher/{id}")
    public ResponseDTO updateTeacher(@PathVariable int id,@RequestBody TeacherDTO teacherDTO) throws JsonProcessingException {
    log.info("Received a request to update Teacher Details. ID:{}. Payload:{}", id, new ObjectMapper().writeValueAsString(teacherDTO));
    return schoolAdminService.updateTeacherDetails(id,teacherDTO);
}
@DeleteMapping("/del-teacher/{id}")
    public ResponseDTO deleteTeacher(@PathVariable int id){
    return schoolAdminService.deleteTeacher(id);
}

@GetMapping("/guardians")
    public ResponseDTO viewAll() throws JsonProcessingException {
    return schoolAdminService.viewAll();
}
@GetMapping("/single-guardian/{id}")
    public ResponseDTO fetchGuardian(@PathVariable int id) throws JsonProcessingException {
    return schoolAdminService.getGuardian(id);
}
@PutMapping("/guardian/{id}")
    public ResponseDTO updateGuardianDetails(@PathVariable int id, @RequestBody GuardianDTO guardianDTO) throws JsonProcessingException {
    return schoolAdminService.updateGuardian(id,guardianDTO);
}
@DeleteMapping("/del-guardian/{id}")
    public ResponseDTO delGuardianInfo(@PathVariable int id){
    return schoolAdminService.delGuardian(id);
}
      }
