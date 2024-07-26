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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('BULK_UPLOAD_STUDENTS')")
    public ResponseDTO bulkUploadStudents(@RequestPart("csvFile")MultipartFile csvFile){
        return schoolAdminService.registerStudentsCSV(csvFile);
    }
    @GetMapping("/students")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_STUDENTS')")
    public ResponseDTO viewStudents() throws JsonProcessingException {
        return schoolAdminService.viewStudents();
    }

    @GetMapping("/student/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_STUDENT')")
    public ResponseDTO fetchOne(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.fetchOne(id);
    }

    @PutMapping("/update-student/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('EDIT_STUDENTS')")
    public ResponseDTO updateStudentDetails(@PathVariable int id, @RequestBody UpdateStudentDTO updateStudentDTO) throws JsonProcessingException, SavingException {
        return schoolAdminService.updateStudent(id, updateStudentDTO);
    }

    @DeleteMapping("/del-student/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('SOFT_DELETE_STUDENTS')")
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
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('BULK_UPLOAD_TEACHERS')")
    public ResponseDTO bulkUploadTeachers(@RequestPart("csvFile")MultipartFile csvFile){
        return schoolAdminService.registerTeacherCSV(csvFile);
    }
    @GetMapping("/teachers")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_TEACHERS')")
    public ResponseDTO viewActiveTeachers() throws JsonProcessingException {
        return schoolAdminService.viewTeachers();
    }

    @GetMapping("/teacher/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_TEACHER')")
    public ResponseDTO fetchATeacher(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.fetchTeacher(id);
    }

    @PutMapping("/teacher/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('EDIT_TEACHER')")
    public ResponseDTO updateTeacher(@PathVariable int id, @RequestBody UpdateTeacherDTO updateTeacherDTO) throws JsonProcessingException {
        log.info("Received a request to update Teacher Details. ID:{}. Payload:{}", id, new ObjectMapper().writeValueAsString(updateTeacherDTO));
        return schoolAdminService.updateTeacherDetails(id, updateTeacherDTO);
    }

    @DeleteMapping("/del-teacher/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('SOFT_DELETE_TEACHERS')")
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
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('CREATE_LEVELS')")
    public ResponseDTO addLevel(@Valid @RequestBody LevelDTO levelDTO) throws JsonProcessingException {
        return schoolAdminService.addLevel(levelDTO);
    }

    @GetMapping("/curriculum/{id}/levels")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_LEVELS')")
    public ResponseDTO getLevelsByCurriculumsId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getLevelsByCurriculumId(id);
    }
    @PutMapping("/level/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('EDIT_LEVELS')")
    public ResponseDTO updateLevel(@PathVariable int id ,@PathParam("levelName") String levelName) throws JsonProcessingException {
        return schoolAdminService.updateLevel(id,levelName);
}

    @DeleteMapping("/level/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('SOFT_DELETE_LEVELS')")
    public ResponseDTO deleteLevel(@PathVariable int id){
        return schoolAdminService.deleteLevel(id);}


    /**
     * LEARNING STAGE
     * @param learningStagesDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    @PostMapping("/learning-stage")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('CREATE_LEARNING_STAGE')")
    public ResponseDTO createLearningStage(@Valid @RequestBody LearningStagesDTO learningStagesDTO) throws JsonProcessingException {
        return schoolAdminService.createLearningStage(learningStagesDTO);
    }

    @GetMapping("/level/{id}/learning-stages")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_LEARNING_STAGES')")
    public ResponseDTO getLearningStageByLevelId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getLearningStagesByLevelId(id);
    }
    @GetMapping("/learning-stages")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_LEARNING_STAGES')")
    public ResponseDTO getLearningStages(){
        return schoolAdminService.getLearningStages();
    }


    @PutMapping("learning-stages/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('EDIT_LEARNING_STAGES')")
    public ResponseDTO updateLearningStage(@PathVariable int id,@PathParam("learningStage") String learningStage) throws JsonProcessingException {
        return schoolAdminService.updateLearningStage(id,learningStage);
    }

    @DeleteMapping("/learning-stage/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('SOFT_DELETE_LEARNING_STAGES')")
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
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('CREATE_SUBJECT)'")
    public ResponseDTO createSubject(@Valid @RequestBody SubjectDTO subjectDTO) throws JsonProcessingException {
        return schoolAdminService.CreateSubject(subjectDTO);
    }
    @GetMapping("/level/{id}/subjects")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_SUBJECTS')")
    public ResponseDTO getSubjectsByLevelId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getSubjectsByLevelId(id);
    }
    @PutMapping("/subjects/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('EDIT_SUBJECT')")
    public ResponseDTO updateSubject(@PathVariable int id,@PathParam("subject") String subject) throws JsonProcessingException {
        return schoolAdminService.updateSubject(id,subject);
    }
    @DeleteMapping("/subject/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('SOFT_DELETE_SUBJECT')")
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
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('CREATE_STREAM')")
    public ResponseDTO createStream(@Valid @RequestBody StreamDTO streamDTO) throws JsonProcessingException {
        return schoolAdminService.AddStream(streamDTO);
    }
    @GetMapping("/school/{id}/streams")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_STREAMS')")
    public ResponseDTO getStreamsBySchoolId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.fetchStreamsBySchoolId(id);
    }
    @PutMapping("/streams/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('EDIT_STREAM')")
    public ResponseDTO updateStream(@PathVariable int id,@PathParam("stream") String stream) throws JsonProcessingException {
        return schoolAdminService.updateStream(id,stream);
    }
    @DeleteMapping("/stream/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('SOFT_DELETE_STREAM')")
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


    /**
     * ASSIGNING SUBJECTS
     * @param subjectsTeacherDTO request dto
     * @return response dto
     */
    @PostMapping("/assign-subject")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('ASSIGN_SUBJECTS_TO_TEACHERS')")
    public ResponseDTO assignSubjectsToTeacher(@Valid @RequestBody SubjectsTeacherDTO subjectsTeacherDTO){
        return schoolAdminService.assignSubjectsToTeacher(subjectsTeacherDTO);
    }
    @GetMapping("/teacher/{id}/subjects")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_SUBJECTS_ASSIGNED_TO_TEACHERS')")
    public ResponseDTO getTeacherSubjects(@PathVariable int id){
        return schoolAdminService.getSubjectsByTeacherId(id);
    }


    /**
     * STUDENT MARKS
     * @param marksDTO request dto
     * @return response dto
     */
    @PostMapping("/marks")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('CREATE_STUDENT_MARKS')")
    public ResponseDTO AddMarks( @Valid @RequestBody MarksDTO marksDTO){
        return schoolAdminService.addMarks(marksDTO);
    }
    @GetMapping("/subject/{id}/student-marks")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('VIEW_STUDENT_MARKS')")
    public ResponseDTO getMarksBySubjectId(@PathVariable int id) throws JsonProcessingException {
        return schoolAdminService.getMarks(id);
    }

    @PutMapping("/marks/{id}")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('EDIT STUDENT_MARKS)'")
    public ResponseDTO updateStudentMarks(@PathVariable int id,@RequestBody MarksDTO marksDTO) throws JsonProcessingException {
        return schoolAdminService.updateStudentMarks(id,marksDTO);
    }

    /**
     * ASSIGN STREAMS
     * @param streamStageDTO the request dto
     * @return response dto
     */

    @PostMapping("/assign-streams-learning-stages")
    @PreAuthorize("hasAnyRole(SchoolAdmin) and hasAnyAuthority('ASSIGN_STREAMS')")
    public ResponseDTO assignStreamToLearningStage(@RequestBody StreamStageDTO streamStageDTO){
        return schoolAdminService.assignStreams(streamStageDTO);
    }
    @GetMapping("/stream/{id}/learning-stage")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_LEARNING_STAGES_STREAMS')")
    public ResponseDTO fetchLearningStagesByStream(@PathVariable int id){
        return schoolAdminService.fetchLearningStageByStream(id);
    }

    @PostMapping("/assign-students-learning-stages")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('ASSIGN_STUDENTS_LEARNING_STAGES')")
    public ResponseDTO assignStudentsToLearningStages(@RequestBody StudentLearningStageDTO stageDTO){
        return schoolAdminService.assignStudentLearningStages(stageDTO);
    }
    @GetMapping("/learning-stage/{id}/students")
    @PreAuthorize("hasAnyRole(SystemAdmin) and hasAnyAuthority('VIEW_STUDENTS_IN_A_LEARNING_STAGES_STREAMS')")
    public ResponseDTO fetchStudentsByLearningStage(@PathVariable int id){
        return schoolAdminService.fetchStudentsByLearningStage(id);
    }







}























