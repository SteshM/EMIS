package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.exception.SavingException;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.AuditTrailUtil;
import com.emis.EMIS.utils.AuthenticatedUser;
import com.emis.EMIS.utils.CsvUtility;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Stella
 * Project school admin service
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolAdminService {
    public final DataService dataService;
    public final CsvUtility csvUtility;
    public final Utilities utilities;
    public final ModelMapper modelMapper;
    private final AuditTrailUtil auditTrailUtil;

//Students

    /**
     * STUDENTS
     * @param file file
     * @return response DTO
     */

    public ResponseDTO registerStudentsCSV(MultipartFile file){
        if(CsvUtility.hasCsvFormat(file)){
            try{
                ArrayList<StudentEntity> students= csvUtility.csvToStudentList(file.getInputStream());
                List<StudentEntity> failedUpload = new ArrayList<>();
                for (StudentEntity student : students){
                    log.info("student = {}", student.toString());
                    if (student.getUser().getFirstName() == null){
                        failedUpload.add(student);
                    }else if (student.getUser().getMiddleName() == null){
                        failedUpload.add(student);
                    }else if (student.getUser().getLastName() == null){
                        failedUpload.add(student);
                    }else if (student.getUser().getEmail() == null){
                        failedUpload.add(student);
                    }else if (student.getUser().getNationality() == null){
                        failedUpload.add(student);
                    }else if (student.getUser().getGender() == null){
                        failedUpload.add(student);
                    }else if (student.getUser().getDateOfBirth() == null){
                        failedUpload.add(student);
                    }else if (student.getRegistrationNo() == null){
                        failedUpload.add(student);
                    }else{
                        dataService.saveUser(student.getUser());
                        dataService.saveStudent(student);
                    }

                }
                return utilities.successResponse("Successfully uploaded students",null);
            }catch(Exception e){
                return utilities.failedResponse(400, STR."could not save students\{e.getLocalizedMessage()}",null);
            }

        }
        return utilities.failedResponse(400,"Wrong file format;could not upload",null);
    }

    public ResponseDTO viewStudents() throws JsonProcessingException {
        List<StudentEntity>studentEntityList = dataService.viewAllStudents();
        log.info("about to fetch students from the dn : {}",studentEntityList.size());
        List<StudentDTO>studentDTOList = studentEntityList.stream()
                .map(student -> {
                    Optional<String>schoolName =Optional.ofNullable(student.getSchools().getSchoolName());
                    return StudentDTO.builder()
                            .studentId(student.getStudentId())
                            .schoolId(student.getSchools() == null?0: student.getSchools().getSchoolId())
                            .schoolName(schoolName.orElse(null))
                            .firstName(student.getUser().getFirstName())
                            .middleName(student.getUser().getMiddleName())
                            .lastName(student.getUser().getLastName())
                            .gender(student.getUser().getGender())
                            .dateOfBirth(student.getUser().getDateOfBirth())
                            .nationality(student.getUser().getNationality())
                            .email(student.getUser().getEmail())
                            .registrationNo(student.getRegistrationNo())
                            .build();
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


    public ResponseDTO updateStudent(int id, UpdateStudentDTO updateStudentDTO) throws JsonProcessingException, SavingException {
        var objectMapper = new ObjectMapper();
        String username= AuthenticatedUser.username();
        var student = dataService.findByStudentId(id);
        log.info("Fetched a Student:{}", objectMapper.writeValueAsString(student));
        student.getUser().setFirstName(updateStudentDTO.getFirstName());
        student.getUser().setMiddleName(updateStudentDTO.getMiddleName());
        student.getUser().setLastName(updateStudentDTO.getLastName());
        student.getUser().setNationality(updateStudentDTO.getNationality());
        student.getUser().setEmail(updateStudentDTO.getEmail());
        student.getUser().setDateOfBirth(updateStudentDTO.getDateOfBirth());
        student.getUser().setGender(updateStudentDTO.getGender());
        student.getSchools().setSchoolName(updateStudentDTO.getSchoolName());
        student.setRegistrationNo(updateStudentDTO.getRegistrationNo());

        log.info("Updated Student Details. About to save:{}", objectMapper.writeValueAsString(student));
        try{
            dataService.saveStudent(student);
            auditTrailUtil.createAuditTrail("Updating student details","Fetched students from the db,updated and saved",1,username);

        }catch (RuntimeException e){
            auditTrailUtil.createAuditTrail("Updating student details",e.getLocalizedMessage(),0,username);
            throw new SavingException(e.getLocalizedMessage());

        }
        return utilities.successResponse("successfully updated student",updateStudentDTO);

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
                ArrayList<TeacherEntity>teacherEntityArrayList =csvUtility.csvToTeacherEntity(file.getInputStream());
                List<TeacherEntity> failedUpload = new ArrayList<>();
                for(TeacherEntity teacher: teacherEntityArrayList){
                    if(teacher.getUser().getFirstName() == null){
                        failedUpload.add(teacher);
                    }else if(teacher.getUser().getMiddleName() == null){
                        failedUpload.add(teacher);
                    } else if(teacher.getUser().getLastName()== null){
                        failedUpload.add(teacher);
                    } else if(teacher.getUser().getEmail() == null){
                        failedUpload.add(teacher);
                    } else if(teacher.getUser().getGender() == null){
                        failedUpload.add(teacher);
                    } else if(teacher.getUser().getPhoneNo() == null){
                        failedUpload.add(teacher);
                    } else if(teacher.getUser().getNationalId() == null){
                        failedUpload.add(teacher);
                    }else if(teacher.getUser().getNationality() == null){
                        failedUpload.add(teacher);
                    }else if(teacher.getUser().getDateOfBirth() == null){
                        failedUpload.add(teacher);
                    }else if(teacher.getTscNo() == null){
                        failedUpload.add(teacher);
                    }else if(teacher.getYearsOfExperience() == 0){
                        failedUpload.add(teacher);
                    }
                    else{
                        dataService.saveUser(teacher.getUser());
                        dataService.saveTeacher(teacher);
                    }
                }
                return utilities.successResponse("List of failed uploads",failedUpload);
            }catch(Exception e){
                return utilities.failedResponse(400,"could not save teachers",null);
            }

        }
        return utilities.failedResponse(400,"Wrong file format;could not upload",null);
    }


    public ResponseDTO viewTeachers() throws JsonProcessingException {
        List<TeacherEntity>teacherEntityList =  dataService.fetchActiveTeachers();
        List<TeacherDTO>teacherDTOList = teacherEntityList.stream()
                .map(teacherEntity -> {
                    Optional<String>schoolName =Optional.ofNullable(teacherEntity.getSchool().getSchoolName());
                  return TeacherDTO.builder()
                            .schoolName(schoolName.orElse(null))
                            .schoolId(teacherEntity.getSchool().getSchoolId()== 0?0:teacherEntity.getSchool().getSchoolId())
                            .firstName(teacherEntity.getUser().getFirstName())
                            .middleName(teacherEntity.getUser().getMiddleName())
                            .lastName(teacherEntity.getUser().getLastName())
                            .gender(teacherEntity.getUser().getGender())
                            .dateOfBirth(teacherEntity.getUser().getDateOfBirth())
                            .nationality(teacherEntity.getUser().getNationality())
                            .nationalId(teacherEntity.getUser().getNationalId())
                            .email(teacherEntity.getUser().getEmail())
                            .phoneNo(teacherEntity.getUser().getPhoneNo())
                            .tscNo(teacherEntity.getTscNo())
                            .yearsOfExperience(teacherEntity.getYearsOfExperience())
                            .build();
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


    public ResponseDTO updateTeacherDetails(int id, UpdateTeacherDTO updateTeacherDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var teacher = dataService.findByTeacherId(id);
        log.info("Fetched Teacher:{}", objectMapper.writeValueAsString(teacher));
        teacher.getUser().setFirstName(updateTeacherDTO.getFirstName());
        teacher.getUser().setMiddleName(updateTeacherDTO.getMiddleName());
        teacher.getUser().setLastName(updateTeacherDTO.getLastName());
        teacher.getUser().setNationality(updateTeacherDTO.getNationality());
        teacher.getUser().setEmail(updateTeacherDTO.getEmail());
        teacher.getUser().setDateOfBirth(updateTeacherDTO.getDateOfBirth());
        teacher.getUser().setGender(updateTeacherDTO.getGender());
        teacher.getUser().setNationalId(updateTeacherDTO.getNationalId());
        teacher.getUser().setPhoneNo(updateTeacherDTO.getPhoneNo());
        teacher.getSchool().setSchoolName(updateTeacherDTO.getSchoolName());
        teacher.setYearsOfExperience(updateTeacherDTO.getYearsOfExperience());
        teacher.setTscNo(updateTeacherDTO.getTscNo());

        log.info("Updated Teacher Details. About to save:{}", objectMapper.writeValueAsString(teacher));
        dataService.saveTeacher(teacher);
        return utilities.successResponse("Updated teacher's details successfully",updateTeacherDTO);

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
                List<GuardianEntity> failedUpload = new ArrayList<>();
                for (GuardianEntity guardian : guardianEntities){
                    if (guardian.getUserEntity().getFirstName() == null) {
                        failedUpload.add(guardian);
                    }else if (guardian.getUserEntity().getMiddleName() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getUserEntity().getLastName() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getUserEntity().getPhoneNo() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getUserEntity().getDateOfBirth() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getUserEntity().getGender() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getUserEntity().getEmail() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getUserEntity().getNationality() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getUserEntity().getNationalId() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getOccupation() == null){
                        failedUpload.add(guardian);
                    }else if (guardian.getEmergencyContact() == null){
                        failedUpload.add(guardian);
                    }else {
                        dataService.saveUser(guardian.getUserEntity());
                        dataService.saveGuardian(guardian);
                    }
                }
                return utilities.successResponse("List of failed  uploads",failedUpload);
            }catch(Exception e){
                return utilities.failedResponse(400,"could not save guardians",null);
            }

        }
        return utilities.failedResponse(400,"Wrong file format;could not upload",null);
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
        LearningStageEntity learningStageEntity = dataService.findByLearningStageId(id).orElseThrow(()-> new RuntimeException("LearningStageId not found"));
        learningStageEntity.setLearningStage(learningStage);
        log.info("Updated learning stage. About to save:{}", objectMapper.writeValueAsString(learningStageEntity));
        dataService.saveLearningStage(learningStageEntity);
        return utilities.successResponse("Updated a learning stage",learningStage);

    }

    public ResponseDTO deleteLearningStage(int id) {
        LearningStageEntity learningStage = dataService.findByLearningStageId(id).orElseThrow(()-> new RuntimeException("LearningStageId not found"));
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
        SubjectEntity subject = new SubjectEntity();
        subject.setLevels(levelsEntity);
        log.info("level : {}",levelsEntity.toString());
        subject.setSubject(subjectDTO.getSubject());
        log.info(" set subject : {}",subject);
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
     * @return response Dto
     */

    public ResponseDTO assignSubjectsToTeacher(SubjectsTeacherDTO subjectsTeacherDTO) {
        TeacherEntity teacher = dataService.findByTeacherId(subjectsTeacherDTO.getTeacherId());
        List<SubjectEntity> subjectEntityList = new ArrayList<>();
        subjectsTeacherDTO.getSubjectIds().forEach(subjectId->{
            SubjectEntity subject = dataService.findBySubjectId(subjectId);
            subject.setTeacher(teacher);
            subjectEntityList.add(subject);
        });
        dataService.saveAllSubjects(subjectEntityList);
        return utilities.successResponse("saved a subject",subjectsTeacherDTO);
    }

    public ResponseDTO getSubjectsByTeacherId(int id) {
        TeacherEntity teacher = dataService.findByTeacherId(id);
        List<SubjectTeacherDTO>subjectTeacherDTOList=dataService.fetchSubjectsByTeacherId(teacher).stream()
                .map(subjectEntity -> {
                    return SubjectTeacherDTO.builder()
                            .subject(subjectEntity.getSubject())
                            .build();
                     })
                .toList();
        var result = TeacherSubjectResDTO.builder()
                .teacherId(id)
                .subjectTeacherDTOList(subjectTeacherDTOList)
                .build();


       return utilities.successResponse("fetched all subjects by teacher id",result);
    }

    public ResponseDTO addMarks(MarksDTO marksDTO) {
        StudentMarksEntity studentMarks = new StudentMarksEntity();
        studentMarks.setStudent(dataService.findByStudentId(marksDTO.getStudentId()));
        studentMarks.setSubject(dataService.findBySubjectId(marksDTO.getSubjectId()));
        studentMarks.setMark(marksDTO.getMark());
        log.info("marks :{}",marksDTO.getMark());
        dataService.saveStudentMarks(studentMarks);
        return utilities.successResponse("saved student marks",null);

    }


    public ResponseDTO getMarks(int id) throws JsonProcessingException {
        SubjectEntity subject = dataService.findBySubjectId(id);
        List<StudentMarksEntity>studentMarksEntityList = dataService.fetchMarksBySubjectId(subject);
        List<MarksResDTO>marksResDTOS =studentMarksEntityList.stream()
                .map(studentMarksEntity -> {
                    return MarksResDTO.builder()
                            .marksId(studentMarksEntity.getMarksId())
                            .mark(studentMarksEntity.getMark())
                            .studentId(studentMarksEntity.getStudent().getStudentId())
                            .subjectId(studentMarksEntity.getSubject().getSubjectId())
                            .subject(studentMarksEntity.getSubject().getSubject())
                            .term(studentMarksEntity.getTerm())
                            .build();
                })
                .toList();
        log.info("fetched all Marks per subject {}",new ObjectMapper().writeValueAsString(studentMarksEntityList));
return utilities.successResponse("successfully fetched all marks per subject",marksResDTOS);

    }


    public ResponseDTO updateStudentMarks(int id,MarksDTO marksDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        StudentMarksEntity studentMarks = dataService.findByMarksId(id);
        studentMarks.setMark(marksDTO.getMark());
        studentMarks.setTerm(marksDTO.getTerm());
        studentMarks.getSubject().setSubjectId(marksDTO.getSubjectId());
        log.info("Updated student mark . About to save:{}", objectMapper.writeValueAsString(studentMarks));
        dataService.saveStudentMarks(studentMarks);
        return utilities.successResponse("Successfully updated student marks",marksDTO);

    }


    public ResponseDTO assignStudentLearningStages(StudentLearningStageDTO stageDTO) {
        StudentEntity student = dataService.findByStudentId(stageDTO.getStudentId());
        Optional<LearningStageEntity> learningStage =dataService.findByLearningStageId(stageDTO.getLearningStageId());
        student.setLearningStage(learningStage.get());
        dataService.saveStudent(student);
        return utilities.successResponse("assigned a student to learning stages",stageDTO);
    }

    public ResponseDTO fetchStudentsByLearningStage(int id) {
        Optional<LearningStageEntity> learningStage = dataService.findByLearningStageId(id);
        List<StudentEntity> students = dataService.fetchStudentsByLearningStageId(learningStage.get());
        List <StudentLearningStageResDTO>stageResDTOS = students.stream()
                .map(student->{
                    return  StudentLearningStageResDTO.builder()
                            .learningStageId(learningStage.get().getLearningStageId())
                            .learningStage(learningStage.get().getLearningStage())
                            .studentId(student.getStudentId())
                            .registrationNo(student.getRegistrationNo())
                            .build();
                })
                .toList();
        return utilities.successResponse("Students in  learning stage",stageResDTOS);
    }


    public ResponseDTO assignStreams(StreamStageDTO streamStageDTO) {
        StreamsEntity streams = dataService.findByStreamId(streamStageDTO.getStreamId());
        Optional<LearningStageEntity>learningStage = dataService.findByLearningStageId(streamStageDTO.getLearningStageId());
        learningStage.get().setStreams(streams);
        dataService.saveLearningStage(learningStage.get());
        return utilities.successResponse("Successfully assigned streams to learning stages",streamStageDTO);
    }

    public ResponseDTO fetchLearningStageByStream(int id) {
        StreamsEntity streamsEntity = dataService.findByStreamId(id);
        List<LearningStageEntity> learningStageEntityList = dataService.findLearningStagesByStream(streamsEntity);
        List<StreamLearningStageDTO> streamLearningStageDTOList = learningStageEntityList.stream()
                .map(learningStage -> {
                    return StreamLearningStageDTO.builder()
                            .learningStage(learningStage.getLearningStage())
                            .stream(learningStage.getStreams().getStream())
                            .build();
                })
                .toList();
        return utilities.successResponse("Successfully fetched learning stages per stream",streamLearningStageDTOList);
    }


}


