package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.CsvUtility;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
     * STUDENTS
     * @param file file
     * @return response DTO
     */

    public ResponseDTO registerStudentsCSV(MultipartFile file){
        if(CsvUtility.hasCsvFormat(file)){
            try{
                ArrayList<StudentEntity> students= CsvUtility.csvToStudentList(file.getInputStream());
                dataService.saveAllStudents(students);
                return utilities.successResponse("successfully saved students",null);
            }catch(Exception e){
                return utilities.failedResponse(205,"could not save students",null);
            }

        }
        return utilities.failedResponse(205,"Wrong file format;could not upload",null);
    }

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

    public ResponseDTO fetchOne(int id) throws JsonProcessingException {
        var student = dataService.findByStudentId(id);
        var studentDTO  = modelMapper.map(student, StudentDTO.class);
        log.info("Fetched student Details:{}", new ObjectMapper().writeValueAsString(student));
        return utilities.successResponse("Successfully fetched a single record",studentDTO);
    }


    public ResponseDTO updateStudent(int id, StudentDTO studentDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var student = dataService.findByStudentId(id);
        log.info("Fetched a Student:{}", objectMapper.writeValueAsString(student));
        modelMapper.map(studentDTO, student);
        log.info("Updated Student Details. About to save:{}", objectMapper.writeValueAsString(student));
        dataService.saveStudent(student);
        return utilities.successResponse("successfully updated student",studentDTO);

    }

    public ResponseDTO deleteStudent(int id) {
        var student = dataService.findByStudentId(id);
        student.setStatus(Status.DELETED);
        student.getUser().setStatus(Status.DELETED);
        dataService.saveStudent(student);
        return utilities.successResponse("deleted a student",null);
    }


    /**
     * TEACHERS
     * @param file file
     * @return response dto
     */

    public ResponseDTO registerTeacherCSV(MultipartFile file){
        if(CsvUtility.hasCsvFormat(file)){
            try{
                ArrayList<TeacherEntity>teacherEntityArrayList =CsvUtility.csvToTeacherEntity(file.getInputStream());
                List<TeacherEntity> failedUpload = new ArrayList<>();
                for(TeacherEntity teacher: teacherEntityArrayList){
                    if(teacher.getUser().getFirstName() == null){
                        failedUpload.add(teacher);
                    }else if(teacher.getUser().getMiddleName() == null){
                        failedUpload.add(teacher);
                    }
                    //else if for every check
                    else{
                        //passed all null checks
                        dataService.saveUser(teacher.getUser());
                        dataService.saveTeacher(teacher);
                    }
                }
                return utilities.successResponse("List of failed uploads",failedUpload);
            }catch(Exception e){
                return utilities.failedResponse(205,"could not save teachers",null);
            }

        }
        return utilities.failedResponse(205,"Wrong file format;could not upload",null);
    }


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

    public ResponseDTO fetchTeacher(int id) throws JsonProcessingException {
        var teacher = dataService.findByTeacherId(id);
        log.info("Fetched Teacher Details:{}", new ObjectMapper().writeValueAsString(teacher));
        var teacherDTO = modelMapper.map(teacher, TeacherDTO.class);
        return utilities.successResponse("fetched  a single teacher",teacherDTO);
    }


    public ResponseDTO updateTeacherDetails(int id, TeacherDTO teacherDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var teacher = dataService.findByTeacherId(id);
        log.info("Fetched Teacher:{}", objectMapper.writeValueAsString(teacher));
        modelMapper.map(teacherDTO, teacher);
        log.info("Updated Teacher Details. About to save:{}", objectMapper.writeValueAsString(teacher));
        dataService.saveTeacher(teacher);
        return utilities.successResponse("Updated teacher's details successfully",teacherDTO);

    }


    public ResponseDTO deleteTeacher(int id) {
        var teacher = dataService.findByTeacherId(id);
        teacher.setStatus(Status.DELETED);
        teacher.getUser().setStatus(Status.DELETED);
        dataService.saveTeacher(teacher);
        return utilities.successResponse("deleted a teacher",null);

    }


    /**
     * GUARDIANS
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

    public ResponseDTO registerGuardiansCSV(MultipartFile file){
        if(CsvUtility.hasCsvFormat(file)){
            try{
                ArrayList<GuardianEntity> guardianEntities= CsvUtility.csvToGuardianEntity(file.getInputStream());
                dataService.saveAlLGuardians(guardianEntities);
                return utilities.successResponse("successfully saved guardians",null);
            }catch(Exception e){
                return utilities.failedResponse(205,"could not save guardians",null);
            }

        }
        return utilities.failedResponse(205,"Wrong file format;could not upload",null);
    }

    public ResponseDTO getGuardian(int id) throws JsonProcessingException {
        var guardian = dataService.findByGuardianId(id);
        log.info("Fetched guardian Details from the db:{}", new ObjectMapper().writeValueAsString(guardian));
        var guardianDTO = modelMapper.map(guardian, GuardianDTO.class);
        return utilities.successResponse("Successfully fetched a guardian",guardianDTO);
    }


    public ResponseDTO updateGuardian(int id, GuardianDTO guardianDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var guardian = dataService.findByGuardianId(id);
        log.info("Fetched a guardian:{}", objectMapper.writeValueAsString(guardian));
        modelMapper.map(guardianDTO,guardian);
        log.info("Updated guardian Details. About to save:{}", objectMapper.writeValueAsString(guardian));
        dataService.saveGuardian(guardian);
        return utilities.successResponse("Updated a guardian's details",guardianDTO);

    }

    public ResponseDTO delGuardian(int id) {
        var guardian = dataService.findByGuardianId(id);
        guardian.setStatus(Status.DELETED);
        guardian.getUserEntity().setStatus(Status.DELETED);
        dataService.saveGuardian(guardian);
        return utilities.successResponse("Successfully deleted guardian details",null);
    }

    /**
     * LEVELS
     * @param levelDTO the request DTO
     * @return response DTO
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO addLevel(LevelDTO levelDTO) throws JsonProcessingException {
        LevelsEntity levelsEntity = modelMapper.map(levelDTO,LevelsEntity.class);
        levelsEntity.setCurriculum(dataService.findByCurriculumId(levelDTO.getCurriculumId()));
        log.info("About to save a level : {}",new ObjectMapper().writeValueAsString(levelsEntity));
         var savedLevel = dataService.saveLevel(levelsEntity);
         var levelResDTO = modelMapper.map(savedLevel, LevelResDTO.class);
        return utilities.successResponse("saved a level",levelResDTO);
    }


    public ResponseDTO getLevelsByCurriculumId(int id) throws JsonProcessingException {
        CurriculumEntity curriculum = dataService.findByCurriculumId(id);
        List<LevelsEntity>levelsEntityList =dataService.findByCurriculum(curriculum);
        log.info("Fetched all levels from the db {}",levelsEntityList);
        List<LevelResDTO>levelResDTOList = levelsEntityList.stream()
                .map(levelsEntity -> {
                    return modelMapper.map(levelsEntity, LevelResDTO.class);
                })
                .toList();
        log.info("fetched all levels per curriculum {}",new ObjectMapper().writeValueAsString(levelsEntityList));
        return utilities.successResponse("Successfully fetched all curriculum levels",levelResDTOList);
    }
    public ResponseDTO updateLevel(int id, String levelName) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        LevelsEntity levelsEntity = dataService.findByLevelId(id);
        log.info("Fetched levels By levelId :{}", objectMapper.writeValueAsString(levelsEntity));
        levelsEntity.setLevelName(levelName);
        log.info("Updated level Details. About to save:{}", objectMapper.writeValueAsString(levelsEntity));
        dataService.saveLevel(levelsEntity);
        return utilities.successResponse("updated a level successfully",levelName);

    }

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
        LevelsEntity levelsEntity = dataService.findByLevelId(learningStagesDTO.getLevelId());
        learningStage.setLevelsEntity(levelsEntity);
        var savedLearningStage =dataService.saveLearningStage(learningStage);
        var learningStageResDTO = modelMapper.map(savedLearningStage, LearningStageResDTO.class);
        return utilities.successResponse("Created a learning stage",learningStageResDTO);
    }

    public ResponseDTO getLearningStagesByLevelId(int id) throws JsonProcessingException {
        LevelsEntity levels = dataService.findByLevelId(id);
        List<LearningStageEntity>learningStageEntityList = dataService.findByLevelsEntity(levels);
        log.info("Fetched all learning stages from the db {}",learningStageEntityList);
        List<LearningStageResDTO>learningStageResDTOList = learningStageEntityList.stream()
                .map(learningStage -> {
                    return modelMapper.map(learningStage,LearningStageResDTO.class);
                })
                .toList();
        log.info("fetched all learning stages per level {}",new ObjectMapper().writeValueAsString(learningStageEntityList));
        return utilities.successResponse("Successfully fetched all learning stages",learningStageResDTOList);
    }

    public ResponseDTO getLearningStages() {
        List<LearningStageEntity> learningStageEntityList = dataService.fetchLearningStages();
        List<LearningStageResDTO>learningStageResDTOList =learningStageEntityList.stream()
                .map(learningStageEntity -> {
                    return modelMapper.map(learningStageEntity, LearningStageResDTO.class);
                })
                .toList();
        log.info("Fetched  learning stages from the db {}",learningStageEntityList);
        return utilities.successResponse("fetched learning stages",learningStageResDTOList);

    }


    public ResponseDTO updateLearningStage(int id, String learningStage) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        LearningStageEntity learningStageEntity = dataService.findByLearningStageId(id);
        learningStageEntity.setLearningStage(learningStage);
        log.info("Updated learning stage. About to save:{}", objectMapper.writeValueAsString(learningStageEntity));
        dataService.saveLearningStage(learningStageEntity);
        return utilities.successResponse("Updated a learning stage",learningStage);

    }

    public ResponseDTO deleteLearningStage(int id) {
        LearningStageEntity learningStage = dataService.findByLearningStageId(id);
        learningStage.setStatus(Status.DELETED);
        learningStage.getLevelsEntity().setStatus(Status.DELETED);
        return utilities.successResponse("deleted a learning stage",null);
    }

    /**
     * SUBJECTS
     * @param subjectDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO CreateSubject(SubjectDTO subjectDTO) throws JsonProcessingException {
        LevelsEntity levelsEntity =dataService.findByLevelId(subjectDTO.getLevelId());
        SubjectEntity subject = modelMapper.map(subjectDTO, SubjectEntity.class);
        subject.setLevels(levelsEntity);
        log.info("About to save a  subject : {}",new ObjectMapper().writeValueAsString(subject));
         var savedSubject = dataService.saveSubject(subject);
         var  subjectResDTO = modelMapper.map(savedSubject, SubjectResDTO.class);
        return utilities.successResponse("Created a subject",subjectResDTO);
    }

    public ResponseDTO getSubjectsByLevelId(int id) throws JsonProcessingException {
        LevelsEntity levelsEntity = dataService.findByLevelId(id);
        List<SubjectEntity>subjectEntityList = dataService.findByLevels(levelsEntity);
        List<SubjectResDTO>subjectResDTOS = subjectEntityList.stream()
                .map(subject -> {
                    return modelMapper.map(subject, SubjectResDTO.class);
                })
                .toList();
        log.info("fetched all subjects per level {}",new ObjectMapper().writeValueAsString(subjectEntityList));
return utilities.successResponse("fetched subjects",subjectResDTOS);
    }


    public ResponseDTO updateSubject(int id, String subject) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        SubjectEntity subjectEntity = dataService.findBySubjectId(id);
        subjectEntity.setSubject(subject);
        log.info("Updated subject. About to save:{}", objectMapper.writeValueAsString(subjectEntity));
        dataService.saveSubject(subjectEntity);
        return utilities.successResponse("Updated a subject",subject);
    }

    public ResponseDTO deleteSubject(int id) {
        SubjectEntity subject = dataService.findBySubjectId(id);
        subject.setStatus(Status.DELETED);
        subject.getLevels().setStatus(Status.DELETED);
        return utilities.successResponse("deleted a subject",null);
    }

    /**
     * STREAMS
     * @param streamDTO the request dto
     * @return response dto
     * @throws JsonProcessingException the exception
     */

    public ResponseDTO AddStream(StreamDTO streamDTO) throws JsonProcessingException {
        StreamsEntity streams = modelMapper.map(streamDTO,StreamsEntity.class);
        SchoolsEntity schools = dataService.findBySchoolId(streamDTO.getSchoolId());
        streams.setSchoolsEntity(schools);
        log.info("About to save a  stream : {}",new ObjectMapper().writeValueAsString(streams));
         streams.setStatus(Status.ACTIVE);
         var savedStream = dataService.saveStream(streams);
         var streamResDTO = modelMapper.map(savedStream, StreamResDTO.class);
        return utilities.successResponse("Saved a stream successfully",streamResDTO);

    }

    public ResponseDTO fetchStreamsBySchoolId(int id) throws JsonProcessingException {
        SchoolsEntity schools = dataService.findBySchoolId(id);
        List<StreamsEntity>streamsEntityList =schools.getStreamsEntityList();
        List<StreamResDTO>streamResDTOList =streamsEntityList.stream()
                .map(streams -> {
                    return modelMapper.map(streams,StreamResDTO.class);
                })
                .toList();
        log.info("fetched all streams per school {}",new ObjectMapper().writeValueAsString(streamsEntityList));
        return utilities.successResponse("successfully fetched all streams",streamResDTOList);
    }

    public ResponseDTO updateStream(int id, String stream) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        StreamsEntity streams = dataService.findByStreamId(id);
        streams.setStream(stream);
        log.info("Updated a stream. About to save:{}", objectMapper.writeValueAsString(streams));
        dataService.saveStream(streams);
        return utilities.successResponse("Updated a stream",stream);

    }

    public ResponseDTO deleteStream(int id) {
        StreamsEntity streams = dataService.findByStreamId(id);
        streams.getSchoolsEntity().setStatus(Status.DELETED);
        streams.setStatus(Status.DELETED);
        dataService.saveStream(streams);
        return utilities.successResponse("Deleted a stream",null);

    }


    /**
     * ASSIGNING GUARDIANS TO STUDENTS
     * @param guardianStudentDTO the request dto
     * @return response dto
     */
    public ResponseDTO assignGuardianToStudent(GuardianStudentDTO guardianStudentDTO) {
        GuardianEntity guardian =dataService.findByGuardianId(guardianStudentDTO.getGuardianId());
        StudentEntity student =dataService.findByStudentId(guardianStudentDTO.getStudentId());
        student.setGuardian(guardian);
        dataService.saveStudent(student);
        return utilities.successResponse("saved a student's guardian",guardianStudentDTO);
    }

    public ResponseDTO getStudentsByGuardianId(int id) {
        GuardianEntity guardian = dataService.findByGuardianId(id);
        List<StudentEntity>studentEntityList = dataService.findStudentsByGuardian(guardian);
        return utilities.successResponse("Fetched all student by guardian",studentEntityList);

    }

    /**
     * ASSIGNING TEACHERS SUBJECTS
     * @param teacherSubjectDTO the request Dto
     * @return response Dto
     */

    public ResponseDTO assignSubjectsToTeacher(TeacherSubjectDTO teacherSubjectDTO) {
        SubjectEntity subject = dataService.findBySubjectId(teacherSubjectDTO.getSubjectId());
        TeacherEntity teacher = dataService.findByTeacherId(teacherSubjectDTO.getTeacherId());
        subject.setTeacher(teacher);
        dataService.saveSubject(subject);
        return utilities.successResponse("saved a teacher",teacherSubjectDTO);
    }

    public ResponseDTO getSubjectsByTeacherId(int id) {
        TeacherEntity teacher = dataService.findByTeacherId(id);
        List<SubjectEntity>subjectEntityList=dataService.fetchSubjectsByTeacherId(teacher);
       return utilities.successResponse("fetched all subjects by teacher id",subjectEntityList);
    }
}


