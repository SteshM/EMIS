package com.emis.EMIS.services;

import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.StudentEntity;
import com.emis.EMIS.models.TeacherEntity;
import com.emis.EMIS.utils.Utilities;
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
    public DataService dataService;
    public Utilities utilities;
    public ModelMapper modelMapper;

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
        return utilities.successResponse("fetched all students",studentDTOList);

    }

    public ResponseDTO fetchOne(int id) {
        var student = dataService.findByStudentId(id);
        var studentDTO  = modelMapper.map(student, StudentDTO.class);
        return utilities.successResponse("Successfully fetched a single record",studentDTO);
    }

    public ResponseDTO updateStudent(int id, StudentDTO studentDTO) {
        StudentEntity student = dataService.findByStudentId(id);
        student.setRegistrationNo(studentDTO.getRegistrationNo());
        var studentDTO1 = modelMapper.map(student, StudentDTO.class);
        dataService.saveStudent(student);
        return utilities.successResponse("successfully updated student",student);

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
                            .nationality(teacherEntity.getUser().getNationality())
                            .yearsOfExperience(teacherEntity.getYearsOfExperience())
                            .build();
                })
                .toList();
        return utilities.successResponse("fetched all active teachers",teacherDTOList);
    }

}
