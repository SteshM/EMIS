package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.StudentEntity;
import com.emis.EMIS.models.TeacherEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.StudentDTO;
import com.emis.EMIS.wrappers.responseDTOs.TeacherDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolAdminService {
    public final DataService dataService;
    public final Utilities utilities;
    public final ModelMapper modelMapper;

    public ResponseDTO viewStudents() {
        List<StudentEntity>studentEntityList = dataService.viewAllStudents();
        List<StudentDTO>studentDTOList = studentEntityList.stream()
                .map(student -> {
                    return StudentDTO.builder()
                            .dateOfBirth(student.getUser().getDateOfBirth())
                            .gender(student.getUser().getGender())
                            .nationality(student.getUser().getNationality())
                            .registrationNo(student.getRegistrationNo())
                            .firstName(student.getUser().getFirstName())
                            .middleName(student.getUser().getMiddleName())
                            .lastName(student.getUser().getLastName())
                            .email(student.getUser().getEmail())
                            .build();


                })
                .toList();
//
////        List<StudentDTO>studentDTOList1 = studentEntityList.stream().map(
////                student -> modelMapper.map(student, StudentDTO.class)
//        ).toList();


        return utilities.successResponse("fetched all students",studentDTOList);

    }

    public ResponseDTO fetchOne(int id) {
        var student = dataService.findByStudentId(id);
        var studentDTO = StudentDTO.builder()
                .gender(student.getUser().getGender())
                .firstName(student.getUser().getFirstName())
                .middleName(student.getUser().getMiddleName())
                .lastName(student.getUser().getLastName())
                .email(student.getUser().getEmail())
                .dateOfBirth(student.getUser().getDateOfBirth())
                .nationality(student.getUser().getNationality())
                .registrationNo(student.getRegistrationNo())
                .build();
//        var studentDTO  = modelMapper.map(student, StudentDTO.class);
        return utilities.successResponse("Successfully fetched a single record",studentDTO);
    }

    public ResponseDTO updateStudent(int id, StudentDTO studentDTO) {
        var student = dataService.findByStudentId(id);
        //old reference
        UserEntity user = student.getUser();
        //new update
        var userEntity = modelMapper.map(studentDTO, UserEntity.class);
        userEntity.setUserId(user.getUserId());
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setEmail(user.getEmail());
        student.setUser(dataService.saveUser(userEntity));
        StudentEntity student1 = dataService.saveStudent(student);
        studentDTO.setRegistrationNo(student1.getRegistrationNo());
        return utilities.successResponse("successfully updated student",studentDTO);

    }

    public ResponseDTO deleteStudent(int id) {
        var student = dataService.findByStudentId(id);
        student.setStatus(Status.DELETED);
        student.getUser().setStatus(Status.DELETED);
        dataService.saveStudent(student);
        return utilities.successResponse("deleted a student",null);
    }


    public ResponseDTO viewTeachers() {
        List<TeacherEntity>teacherEntityList =  dataService.fetchActiveTeachers();
        List<TeacherDTO>teacherDTOList = teacherEntityList.stream()
                .map(teacherEntity -> {
                    return TeacherDTO.builder()
                            .tscNo(teacherEntity.getTscNo())
                            .gender(teacherEntity.getUser().getGender())
                            .dateOfBirth(teacherEntity.getUser().getDateOfBirth())
                            .firstName(teacherEntity.getUser().getFirstName())
                            .middleName(teacherEntity.getUser().getMiddleName())
                            .lastName(teacherEntity.getUser().getLastName())
                            .email(teacherEntity.getUser().getEmail())
                            .nationalId(teacherEntity.getUser().getNationalId())
                            .phoneNo(teacherEntity.getUser().getPhoneNo())
                            .nationality(teacherEntity.getUser().getNationality())
                            .yearsOfExperience(teacherEntity.getYearsOfExperience())
                            .build();
                })
                .toList();
        return utilities.successResponse("fetched all active teachers",teacherDTOList);
    }

    public ResponseDTO fetchTeacher(int id) {
        TeacherEntity teacher = dataService.findByTeacherId(id);
        var teacherDTO = TeacherDTO.builder()
                .firstName(teacher.getUser().getFirstName())
                .middleName(teacher.getUser().getMiddleName())
                .lastName(teacher.getUser().getLastName())
                .email(teacher.getUser().getEmail())
                .phoneNo(teacher.getUser().getPhoneNo())
                .gender(teacher.getUser().getGender())
                .dateOfBirth(teacher.getUser().getDateOfBirth())
                .nationality(teacher.getUser().getNationality())
                .nationalId(teacher.getUser().getNationalId())
                .tscNo(teacher.getTscNo())
                .yearsOfExperience(teacher.getYearsOfExperience())
                .build();
        return utilities.successResponse("fetched  a single teacher",teacherDTO);
    }

    public ResponseDTO updateTeacherDetails(int id, TeacherDTO teacherDTO) {
        var teacher = dataService.findByTeacherId(id);
        var user = teacher.getUser();
        var userEntity =modelMapper.map(teacherDTO, UserEntity.class);
        userEntity.setUserId(user.getUserId());
        userEntity.setGender(user.getGender());
        teacher.setUser(dataService.saveUser(userEntity));
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
}
