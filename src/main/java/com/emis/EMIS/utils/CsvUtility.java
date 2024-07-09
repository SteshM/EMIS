package com.emis.EMIS.utils;

import com.emis.EMIS.models.GuardianEntity;
import com.emis.EMIS.models.StudentEntity;
import com.emis.EMIS.models.TeacherEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Component
public class CsvUtility {
    public static ArrayList<StudentEntity> csvToStudentList(InputStream is) {
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(bReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            ArrayList<StudentEntity> studentEntitiesList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                StudentEntity student = new StudentEntity();
                student.getUser().setFirstName(csvRecord.get("First Name"));
                student.getUser().setMiddleName(csvRecord.get("Middle Name"));
                student.getUser().setLastName(csvRecord.get("Last Name"));
                student.getUser().setDateOfBirth(csvRecord.get("Date Of birth"));
                student.getUser().setEmail(csvRecord.get("Email"));
                student.getUser().setGender(csvRecord.get("Gender"));
                student.getUser().setNationality(csvRecord.get("Nationality"));
                student.setRegistrationNo(csvRecord.get("RegistrationNo"));
                studentEntitiesList.add(student);
            }
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
                teacher.getUser().setDateOfBirth(csvRecord.get("Date Of birth"));
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
