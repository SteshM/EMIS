package com.emis.EMIS.utils;

import com.emis.EMIS.models.GuardianEntity;
import com.emis.EMIS.models.StudentEntity;
import com.emis.EMIS.models.TeacherEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.repositories.UserRepo;
import com.emis.EMIS.repositories.UserRoleRepo;
import com.emis.EMIS.services.DataService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Service
public class CsvUtility {
    private  final DataService dataService;
    private  final UserRepo userRepo;

    private static final Logger log = LoggerFactory.getLogger(CsvUtility.class);



    public  ArrayList<StudentEntity> csvToStudentList(InputStream is) {


        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(bReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            ArrayList<StudentEntity> studentEntitiesList = new ArrayList<>();

            ArrayList<UserEntity> userEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                StudentEntity student = new StudentEntity();
                UserEntity user = new UserEntity();

                log.info("record email = {}",csvRecord.get("RegistrationNo"));
                user.setFirstName(csvRecord.get("FirstName"));
                student.setRegistrationNo(csvRecord.get("RegistrationNo"));
                user.setMiddleName(csvRecord.get("MiddleName"));
                user.setLastName(csvRecord.get("LastName"));
                user.setDateOfBirth(csvRecord.get("DateOfBirth"));
                user.setEmail(csvRecord.get("Email"));
                user.setGender(csvRecord.get("Gender"));
                user.setNationality(csvRecord.get("Nationality"));
                student.setRegistrationNo(csvRecord.get("RegistrationNo"));
                log.info("record RegistrationNo ={}",csvRecord.get("RegistrationNo"));
                log.info("adding to list");
                studentEntitiesList.add(student);
                userEntities.add(user);
                UserEntity savedUser=userRepo.save(user);
                student.setUser(savedUser);
                dataService.saveStudent(student);

                log.info("list contains {} items",studentEntitiesList.size());
            }


            log.info("list contains {} items",studentEntitiesList.size());
            return studentEntitiesList;
        } catch (IOException e) {
            throw new RuntimeException("CSV data  failed to parse: " + e.getMessage());
        }

    }

    public static ArrayList<GuardianEntity> csvToGuardianEntity(InputStream is) {
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(bReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            ArrayList<GuardianEntity> guardianEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                GuardianEntity guardian = new GuardianEntity();
                guardian.getUserEntity().setFirstName(csvRecord.get("First Name"));
                guardian.getUserEntity().setMiddleName(csvRecord.get("Middle Name"));
                guardian.getUserEntity().setLastName(csvRecord.get("Last Name"));
                guardian.getUserEntity().setDateOfBirth(csvRecord.get("Date Of birth"));
                guardian.getUserEntity().setEmail(csvRecord.get("Email"));
                guardian.getUserEntity().setGender(csvRecord.get("Gender"));
                guardian.getUserEntity().setNationality(csvRecord.get("Nationality"));
                guardian.getUserEntity().setNationalId(csvRecord.get("National Id"));
                guardian.getUserEntity().setPhoneNo(csvRecord.get("Phone No"));
                guardian.setOccupation(csvRecord.get("Occupation"));
                guardian.setRelationship(csvRecord.get("Relationship"));
                guardian.setEmergencyContact(csvRecord.get("Emergency Contact"));
                guardianEntities.add(guardian);
            }
            return guardianEntities;
        } catch (IOException e) {
            throw new RuntimeException("CSV data  failed to parse: " + e.getMessage());
        }
    }

    public static ArrayList<TeacherEntity> csvToTeacherEntity(InputStream is) {
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(bReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            ArrayList<TeacherEntity> teacherEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                TeacherEntity teacher = new TeacherEntity();
                teacher.getUser().setFirstName(csvRecord.get("First Name"));
                teacher.getUser().setMiddleName(csvRecord.get("Middle Name"));
                teacher.getUser().setLastName(csvRecord.get("Last Name"));
                teacher.getUser().setDateOfBirth(csvRecord.get("Date Of Birth"));
                teacher.getUser().setEmail(csvRecord.get("Email"));
                teacher.getUser().setGender(csvRecord.get("Gender"));
                teacher.getUser().setNationalId(csvRecord.get("National Id"));
                teacher.getUser().setNationality(csvRecord.get("Nationality"));
                teacher.getUser().setPhoneNo(csvRecord.get("Phone No"));
                teacher.setTscNo(csvRecord.get("Occupation"));
                teacher.setYearsOfExperience(Integer.valueOf(csvRecord.get("years of experience")));
                teacherEntities.add(teacher);
            }
            return teacherEntities;
        } catch (IOException e) {
            throw new RuntimeException("CSV data  failed to parse: " + e.getMessage());
        }
    }

    public static boolean hasCsvFormat(MultipartFile file) {
        if(file.getContentType().equals("text/csv")){
            return true;
        }return false;
    }
}
