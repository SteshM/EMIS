package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.GuardianEntity;
import com.emis.EMIS.models.StudentEntity;
import com.emis.EMIS.models.TeacherEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.responseDTOs.GuardianDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.StudentDTO;
import com.emis.EMIS.wrappers.responseDTOs.TeacherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Stella
 * Project school admin service
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolAdminService {
    public final DataService dataService;
    public final Utilities utilities;
    public final ModelMapper modelMapper;

//Students

    /**
     * Method to view student details
     * @return the responseDTO
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO viewStudents() throws JsonProcessingException {
        List<StudentEntity>studentEntityList = dataService.viewAllStudents();
        List<StudentDTO>studentDTOList = studentEntityList.stream()
                .map(student -> {
                    return modelMapper.map(student,StudentDTO.class);
                })
                .toList();
        log.info("Fetched  all student Details:{}", new ObjectMapper().writeValueAsString(studentEntityList));
        return utilities.successResponse("fetched all  active students",studentDTOList);

    }

    /**
     *
     * @param id the student id
     * @return ResponseDTO
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO fetchOne(int id) throws JsonProcessingException {
        var student = dataService.findByStudentId(id);
        var studentDTO  = modelMapper.map(student, StudentDTO.class);
        log.info("Fetched student Details:{}", new ObjectMapper().writeValueAsString(student));
        return utilities.successResponse("Successfully fetched a single record",studentDTO);
    }

    /**
     * A method to update a Student
     * @param id the studentId
     * @param studentDTO the Student dto
     * @return ResponseDTO
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO updateStudent(int id, StudentDTO studentDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var student = dataService.findByStudentId(id);
        log.info("Fetched a Student:{}", objectMapper.writeValueAsString(student));
        modelMapper.map(studentDTO, student);
        log.info("Updated Student Details. About to save:{}", objectMapper.writeValueAsString(student));
        dataService.saveStudent(student);
        return utilities.successResponse("successfully updated student",studentDTO);

    }

    /**
     * A method to delete a student
     * @param id the studentId
     * @return ResponseDTO
     */

    public ResponseDTO deleteStudent(int id) {
        var student = dataService.findByStudentId(id);
        student.setStatus(Status.DELETED);
        student.getUser().setStatus(Status.DELETED);
        dataService.saveStudent(student);
        return utilities.successResponse("deleted a student",null);
    }

    /**
     * A method to view teachers
     * @return ResponseDTO
     * @throws JsonProcessingException the exception
     */


    //Teachers

    public ResponseDTO viewTeachers() throws JsonProcessingException {
        List<TeacherEntity>teacherEntityList =  dataService.fetchActiveTeachers();
        List<TeacherDTO>teacherDTOList = teacherEntityList.stream()
                .map(teacherEntity -> {

                    return modelMapper.map(teacherEntity, TeacherDTO.class);
                })
                .toList();
        log.info("Fetched  all teachers Details:{}", new ObjectMapper().writeValueAsString(teacherEntityList));
        return utilities.successResponse("fetched all active teachers",teacherDTOList);
    }

    /**
     * Created a method to fetch a single teacher
     * @param id the teacher id
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO fetchTeacher(int id) throws JsonProcessingException {
        var teacher = dataService.findByTeacherId(id);
        log.info("Fetched Teacher Details:{}", new ObjectMapper().writeValueAsString(teacher));
        var teacherDTO = modelMapper.map(teacher, TeacherDTO.class);
        return utilities.successResponse("fetched  a single teacher",teacherDTO);
    }

    /**
     * A method to update a teacher details
     * @param id teacher id
     * @param teacherDTO the teacher dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO updateTeacherDetails(int id, TeacherDTO teacherDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var teacher = dataService.findByTeacherId(id);
        log.info("Fetched Teacher:{}", objectMapper.writeValueAsString(teacher));
        modelMapper.map(teacherDTO, teacher);
        log.info("Updated Teacher Details. About to save:{}", objectMapper.writeValueAsString(teacher));
        dataService.saveTeacher(teacher);
        return utilities.successResponse("Updated teacher's details successfully",teacherDTO);

    }

    /**
     * soft deleting a teachers record
     * @param id the teacher id
     * @return response dto
     */

    public ResponseDTO deleteTeacher(int id) {
        var teacher = dataService.findByTeacherId(id);
        teacher.setStatus(Status.DELETED);
        teacher.getUser().setStatus(Status.DELETED);
        dataService.saveTeacher(teacher);
        return utilities.successResponse("deleted a teacher",null);

    }

    /**
     * A request to view all active guardians in the system
     * @return the response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO viewAll() throws JsonProcessingException {
        List<GuardianEntity>guardianEntityList = dataService.fetchActiveGuardians();
        List<GuardianDTO>guardianDTOList = guardianEntityList.stream()
                .map(guardianEntity -> {
                    return modelMapper.map(guardianEntity, GuardianDTO.class);
                })
                .toList();
        log.info("Fetched  all guardian Details:{}", new ObjectMapper().writeValueAsString(guardianEntityList));
        return utilities.successResponse("Fetched all guardians",guardianDTOList);
    }

    /**
     * Fetching a guardian from the db
     * @param id the guardian id
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO getGuardian(int id) throws JsonProcessingException {
        var guardian = dataService.findByGuardianId(id);
        log.info("Fetched guardian Details from the db:{}", new ObjectMapper().writeValueAsString(guardian));
        var guardianDTO = modelMapper.map(guardian, GuardianDTO.class);
        return utilities.successResponse("Successfully fetched a guardian",guardianDTO);
    }

    /**
     * Fetching guardians details from the db , updating and saving them
     * @param id the guardian id
     * @param guardianDTO the guardian dto
     * @return the response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO updateGuardian(int id, GuardianDTO guardianDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var guardian = dataService.findByGuardianId(id);
        log.info("Fetched a guardian:{}", objectMapper.writeValueAsString(guardian));
        modelMapper.map(guardianDTO,guardian);
        log.info("Updated guardian Details. About to save:{}", objectMapper.writeValueAsString(guardian));
        dataService.saveGuardian(guardian);
        return utilities.successResponse("Updated a guardian's details",guardianDTO);

    }

    /**
     * soft deleting a guardian's record
     * @param id the guardian id
     * @return the response dto
     */
    public ResponseDTO delGuardian(int id) {
        var guardian = dataService.findByGuardianId(id);
        guardian.setStatus(Status.DELETED);
        guardian.getUserEntity().setStatus(Status.DELETED);
        dataService.saveGuardian(guardian);
        return utilities.successResponse("Successfully deleted guardian details",null);
    }
}
