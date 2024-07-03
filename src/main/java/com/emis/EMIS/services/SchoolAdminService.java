package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.*;
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

    public ResponseDTO addLevel(LevelDTO levelDTO) throws JsonProcessingException {
        LevelsEntity levelsEntity = modelMapper.map(levelDTO,LevelsEntity.class);
        log.info("About to save a level : {}",new ObjectMapper().writeValueAsString(levelsEntity));
         var savedLevel = dataService.saveLevel(levelsEntity);
         var levelResDTO = modelMapper.map(savedLevel, LevelResDTO.class);
        return utilities.successResponse("saved a level",levelResDTO);
    }


    public ResponseDTO getLevelsByCurriculumId(int id) throws JsonProcessingException {
        List<LevelsEntity>levelsEntityList =dataService.fetchAllLevels();
        log.info("Fetched all levels from the db {}",levelsEntityList);
        List<LevelDTO>levelDTOList = levelsEntityList.stream()
                .map(levelsEntity -> {
                    return modelMapper.map(levelsEntity, LevelDTO.class);
                })
                .toList();
        log.info("fetched all levels per curriculum {}",new ObjectMapper().writeValueAsString(levelsEntityList));
        return utilities.successResponse("Successfully fetched all curriculum levels",levelDTOList);
    }


//    public ResponseDTO updateLevel(int id, String levelName) throws JsonProcessingException {
//        var objectMapper = new ObjectMapper();
//        LevelsEntity levelsEntity = dataService.findByCurriculumIdAndLevelId(id,id);
//        log.info("Fetched levels By levelId and curriculumId:{}", objectMapper.writeValueAsString(levelsEntity));
//        levelsEntity.setLevelName(levelName);
//        log.info("Updated level Details. About to save:{}", objectMapper.writeValueAsString(levelsEntity));
//        dataService.saveLevel(levelsEntity);
//        return utilities.successResponse("updated a level successfully",levelName);
//
//    }


    public ResponseDTO deleteLevel(int id) {
        LevelsEntity levelsEntity = dataService.findByLevelId(id);
        levelsEntity.setStatus(Status.DELETED);
        levelsEntity.getCurriculum().setStatus(Status.DELETED);
        return utilities.successResponse("deleted a level",null);
    }


    /**
     * LEARNING STAGE
     * @param learningStagesDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO createLearningStage(LearningStagesDTO learningStagesDTO) throws JsonProcessingException {
        LearningStageEntity learningStage = modelMapper.map(learningStagesDTO,LearningStageEntity.class);
        log.info("About to save a learning stage : {}",new ObjectMapper().writeValueAsString(learningStage));
        var savedLearningStage =dataService.saveLearningStage(learningStage);
        var learningStageResDTO = modelMapper.map(savedLearningStage, LearningStageResDTO.class);
        return utilities.successResponse("Created a learning stage",learningStageResDTO);
    }

    public ResponseDTO getLearningStagesByLevelId(int id) throws JsonProcessingException {
        List<LearningStageEntity>learningStageEntityList =dataService.fetchLearningStages();
        log.info("Fetched all learning stages from the db {}",learningStageEntityList);
        List<LearningStagesDTO>learningStagesDTOList = learningStageEntityList.stream()
                .map(learningStage -> {
                    return modelMapper.map(learningStage,LearningStagesDTO.class);
                })
                .toList();
        log.info("fetched all learning stages per level {}",new ObjectMapper().writeValueAsString(learningStageEntityList));
        return utilities.successResponse("Successfully fetched all learning stages",learningStagesDTOList);
    }


    public ResponseDTO deleteLearningStage(int id) {
        LearningStageEntity learningStage = dataService.findByLearningStageId(id);
        learningStage.setStatus(Status.DELETED);
        learningStage.getLevelsEntity().setStatus(Status.DELETED);
        return utilities.successResponse("deleted a learning stage",null);
    }

    public ResponseDTO CreateSubject(SubjectDTO subjectDTO) throws JsonProcessingException {
        SubjectEntity subject = modelMapper.map(subjectDTO, SubjectEntity.class);
        log.info("About to save a  subject : {}",new ObjectMapper().writeValueAsString(subject));
         var savedSubject = dataService.saveSubject(subject);
         var  subjectResDTO = modelMapper.map(savedSubject, SubjectResDTO.class);
        return utilities.successResponse("Created a subject",subjectResDTO);
    }

    public ResponseDTO getSubjectsByLevelId(int id) throws JsonProcessingException {
        List<SubjectEntity>subjectEntityList = dataService.fetchSubjects();
        List<SubjectDTO>subjectDTOS = subjectEntityList.stream()
                .map(subject -> {
                    return modelMapper.map(subject, SubjectDTO.class);
                })
                .toList();
        log.info("fetched all subjects per level {}",new ObjectMapper().writeValueAsString(subjectEntityList));
return utilities.successResponse("fetched subjects",subjectDTOS);
    }


    public ResponseDTO deleteSubject(int id) {
        SubjectEntity subject = dataService.findBySubjectId(id);
        subject.setStatus(Status.DELETED);
        subject.getLevels().setStatus(Status.DELETED);
        return utilities.successResponse("deleted a subject",null);
    }

    public ResponseDTO AddStream(StreamDTO streamDTO) throws JsonProcessingException {
        StreamsEntity streams = modelMapper.map(streamDTO,StreamsEntity.class);
        log.info("About to save a  stream : {}",new ObjectMapper().writeValueAsString(streams));
         var savedStream = dataService.saveStream(streams);
         var streamResDTO = modelMapper.map(savedStream, StreamResDTO.class);
        return utilities.successResponse("Saved a stream successfully",streamResDTO);

    }

    public ResponseDTO fetchStreamsBySchoolId(int id) throws JsonProcessingException {
        List<StreamsEntity>streamsEntityList = dataService.fetchStreams();
        List<StreamDTO>streamDTOList =streamsEntityList.stream()
                .map(streams -> {
                    return modelMapper.map(streams,StreamDTO.class);
                })
                .toList();
        log.info("fetched all streams per school {}",new ObjectMapper().writeValueAsString(streamsEntityList));
        var streamResDTO = modelMapper.map(streamsEntityList,StreamResDTO.class);
        return utilities.successResponse("successfully fetched all streams",streamResDTO);
    }

    public ResponseDTO deleteStream(int id) {
        StreamsEntity streams = dataService.findByStreamId(id);
        streams.getSchoolsEntity().setStatus(Status.DELETED);
        streams.setStatus(Status.DELETED);
        dataService.saveStream(streams);
        return utilities.successResponse("Deleted a stream",null);

    }
}


