package com.emis.EMIS.controllers;

import com.emis.EMIS.exception.CustomExceptionHandler;
import com.emis.EMIS.exception.SavingException;
import com.emis.EMIS.services.SchoolAdminService;
import com.emis.EMIS.wrappers.requestDTOs.*;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/schAdmin")
@CrossOrigin("*")
public class    SchoolAdminController {

    private final SchoolAdminService schoolAdminService;

    /**
     * STUDENTS
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    @PostMapping("/upload-students")
    public ResponseDTO bulkUploadStudents(@RequestPart("csvFile")MultipartFile csvFile){
        return schoolAdminService.registerStudentsCSV(csvFile);
    }
    @GetMapping("/students")
    public ResponseDTO viewStudents() throws JsonProcessingException {
        return schoolAdminService.viewStudents();
    }

    @GetMapping("/student/{id}")
    public ResponseDTO fetchOne(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.fetchOne(id);
    }

    @PutMapping("/update-student/{id}")
    public ResponseDTO updateStudentDetails(@PathVariable int id, @RequestBody StudentDTO studentDTO) throws JsonProcessingException, SavingException {
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



    @PostMapping("/upload-teachers")
    public ResponseDTO bulkUploadTeachers(@RequestPart("csvFile")MultipartFile csvFile){
        return schoolAdminService.registerTeacherCSV(csvFile);
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
    public ResponseDTO addLevel(@Valid @RequestBody LevelDTO levelDTO) throws JsonProcessingException {
        return schoolAdminService.addLevel(levelDTO);
    }

    @GetMapping("/curriculum/{id}/levels")
    public ResponseDTO getLevelsByCurriculumsId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getLevelsByCurriculumId(id);
    }
    @PutMapping("/level/{id}")
    public ResponseDTO updateLevel(@PathVariable int id ,@PathParam("levelName") String levelName) throws JsonProcessingException {
        return schoolAdminService.updateLevel(id,levelName);
}

    @DeleteMapping("/level/{id}")
    public ResponseDTO deleteLevel(@PathVariable int id){
        return schoolAdminService.deleteLevel(id);}


    /**
     * LEARNING STAGE
     * @param learningStagesDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/learning-stage")
    public ResponseDTO createLearningStage(@Valid @RequestBody LearningStagesDTO learningStagesDTO) throws JsonProcessingException {
        return schoolAdminService.createLearningStage(learningStagesDTO);
    }

    @GetMapping("/level/{id}/learning-stages")
    public ResponseDTO getLearningStageByLevelId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getLearningStagesByLevelId(id);
    }
    @GetMapping("/learning-stages")
    public ResponseDTO getLearningStages(){
        return schoolAdminService.getLearningStages();
    }


    @PutMapping("learning-stages/{id}")
    public ResponseDTO updateLearningStage(@PathVariable int id,@PathParam("learningStage") String learningStage) throws JsonProcessingException {
        return schoolAdminService.updateLearningStage(id,learningStage);
    }

    @DeleteMapping("/learning-stage/{id}")
    public ResponseDTO deleteLearningStage(@PathVariable int id){
        return schoolAdminService.deleteLearningStage(id);
    }


    /**
     * SUBJECTS
     * @param subjectDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/subject")
    public ResponseDTO createSubject(@Valid @RequestBody SubjectDTO subjectDTO) throws JsonProcessingException {
        return schoolAdminService.CreateSubject(subjectDTO);
    }
    @GetMapping("/level/{id}/subjects")
    public ResponseDTO getSubjectsByLevelId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getSubjectsByLevelId(id);
    }
    @PutMapping("/subjects/{id}")
    public ResponseDTO updateSubject(@PathVariable int id,@PathParam("subject") String subject) throws JsonProcessingException {
        return schoolAdminService.updateSubject(id,subject);
    }
    @DeleteMapping("/subject/{id}")
    public ResponseDTO deleteSubject(@PathVariable int id){
        return schoolAdminService.deleteSubject(id);
    }


    /**
     * STREAMS
     * @param streamDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/stream")
    public ResponseDTO createStream(@Valid @RequestBody StreamDTO streamDTO) throws JsonProcessingException {
        return schoolAdminService.AddStream(streamDTO);
    }
    @GetMapping("/school/{id}/streams")
    public ResponseDTO getStreamsBySchoolId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.fetchStreamsBySchoolId(id);
    }
    @PutMapping("/streams/{id}")
    public ResponseDTO updateStream(@PathVariable int id,@PathParam("stream") String stream) throws JsonProcessingException {
        return schoolAdminService.updateStream(id,stream);
    }
    @DeleteMapping("/stream/{id}")
    public ResponseDTO deleteStream(@PathVariable int id){
        return schoolAdminService.deleteStream(id);
    }

    @PostMapping("/assign-guardian")
    public ResponseDTO assignGuardianToStudent(@RequestBody GuardianStudentDTO guardianStudentDTO){
        return schoolAdminService.assignGuardianToStudent(guardianStudentDTO);
    }
    @GetMapping("/guardian/{id}/students")
    public ResponseDTO getStudentsByGuardianId(@PathVariable int id){
        return schoolAdminService.getStudentsByGuardianId(id);
    }

    @PostMapping("/assign-subject")
    public ResponseDTO assignSubjectsToTeacher(@RequestBody TeacherSubjectDTO teacherSubjectDTO){
        return schoolAdminService.assignSubjectsToTeacher(teacherSubjectDTO);
    }
    @GetMapping("/teacher/{id}/subjects")
    public ResponseDTO getTeacherSubjects(@PathVariable int id){
        return schoolAdminService.getSubjectsByTeacherId(id);
    }
    @PostMapping("/marks")
    public ResponseDTO AddMarks(@RequestBody MarksDTO marksDTO){
        return schoolAdminService.addMarks(marksDTO);
    }
    @GetMapping("/subject/{id}/student-marks")
    public ResponseDTO getMarksBySubjectId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getMarks(id);
    }

    @PutMapping("/marks/{id}")
    public ResponseDTO updateStudentMarks(@PathVariable int id,@RequestBody MarksDTO marksDTO) throws JsonProcessingException {
        return schoolAdminService.updateStudentMarks(id,marksDTO);
    }

    @PostMapping("/resource")
    public ResponseDTO addResource(@RequestBody ResourceDTO resourceDTO) throws JsonProcessingException {
        return schoolAdminService.createResource(resourceDTO);
    }
    @GetMapping("/resources")
    public ResponseDTO fetchResources(){
        return schoolAdminService.AllResources();
    }

    @PostMapping("assign-students-learning-stages")
    public ResponseDTO assignStudentsToLearningStages(@RequestBody StudentLearningStageDTO stageDTO){
        return schoolAdminService.assignStudentLearningStages(stageDTO);
    }

}























