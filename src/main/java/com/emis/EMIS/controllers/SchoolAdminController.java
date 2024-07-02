package com.emis.EMIS.controllers;

import com.emis.EMIS.services.SchoolAdminService;
import com.emis.EMIS.wrappers.requestDTOs.LevelDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.GuardianDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.StudentDTO;
import com.emis.EMIS.wrappers.responseDTOs.TeacherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/schAdmin")
public class SchoolAdminController {

    private final SchoolAdminService schoolAdminService;

    /**
     * STUDENTS
     *
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @GetMapping("/students")
    public ResponseDTO viewStudents() throws JsonProcessingException {
        return schoolAdminService.viewStudents();
    }

    @GetMapping("/student/{id}")
    public ResponseDTO fetchOne(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.fetchOne(id);
    }

    @PutMapping("/update-student/{id}")
    public ResponseDTO updateStudentDetails(@PathVariable int id, @RequestBody StudentDTO studentDTO) throws JsonProcessingException {
        return schoolAdminService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/del-student/{id}")
    public ResponseDTO softDelete(@PathVariable int id) {
        return schoolAdminService.deleteStudent(id);
    }


    /**
     * TEACHERS
     *
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    @GetMapping("/teachers")
    public ResponseDTO viewActiveTeachers() throws JsonProcessingException {
        return schoolAdminService.viewTeachers();
    }

    @GetMapping("/teacher/{id}")
    public ResponseDTO fetchATeacher(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.fetchTeacher(id);
    }

    @PutMapping("/teacher/{id}")
    public ResponseDTO updateTeacher(@PathVariable int id, @RequestBody TeacherDTO teacherDTO) throws JsonProcessingException {
        log.info("Received a request to update Teacher Details. ID:{}. Payload:{}", id, new ObjectMapper().writeValueAsString(teacherDTO));
        return schoolAdminService.updateTeacherDetails(id, teacherDTO);
    }

    @DeleteMapping("/del-teacher/{id}")
    public ResponseDTO deleteTeacher(@PathVariable int id) {
        return schoolAdminService.deleteTeacher(id);
    }


    /**
     * GUARDIANS
     *
     * @return response dto
     * @throws JsonProcessingException the exception
     */
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
        return schoolAdminService.updateGuardian(id, guardianDTO);
    }

    @DeleteMapping("/del-guardian/{id}")
    public ResponseDTO delGuardianInfo(@PathVariable int id) {
        return schoolAdminService.delGuardian(id);
    }


    /**
     * LEVELS
     * @param levelDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/level")
    public ResponseDTO addLevel(@RequestBody LevelDTO levelDTO) throws JsonProcessingException {
        return schoolAdminService.addLevel(levelDTO);
    }

    @GetMapping("/curriculum/{id}/levels")
    public ResponseDTO getLevelsByCurriculumsId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getLevelsByCurriculumId(id);
    }
//@PutMapping("/curriculum/{id}/level/{id}")
//    public ResponseDTO updateLevel(@PathVariable int id ,@PathParam("levelName") String levelName) throws JsonProcessingException {
//        return schoolAdminService.updateLevel(id,levelName);
//}

    @DeleteMapping("/level/{id}")
    public ResponseDTO deleteLevel(@PathVariable int id){
        return schoolAdminService.deleteLevel(id);}




}






















