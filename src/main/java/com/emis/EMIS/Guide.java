package com.emis.EMIS;

import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;

public interface Guide {
    public ResponseDTO addAuth();//SuperAdmin & Admin
    public ResponseDTO addAdmin();//SuperAdmin

    //EdvodAdmin
    public ResponseDTO enrolAgent();
           //CRUD
    public ResponseDTO AddSchoolAdmin();
    public ResponseDTO UpdateSchoolAdmin();
    public ResponseDTO getSchoolAdmins();
    public ResponseDTO getSchoolAdminById();
    public ResponseDTO deleteSchoolAdminById();

    //TODO LIST MONDAY
    /* Check out
    * agents service registering a school....things like CURRICULUM */
    /* Tackle Guardians(rel with students),
    * Classes
    * Grades
    * Subjects
    * levels*/



  




}
