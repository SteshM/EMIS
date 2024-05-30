package com.emis.EMIS;

import com.emis.EMIS.wrappers.ResponseDTO;

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

    //Agents
  




}
