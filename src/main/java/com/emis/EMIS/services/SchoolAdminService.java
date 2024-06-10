package com.emis.EMIS.services;

import com.emis.EMIS.models.StudentEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.StudentDTO;
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
                            .dateOfBirth(student.getDateOfBirth())
                            .gender(student.getGender())
                            .nationality(student.getNationality())
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
}
