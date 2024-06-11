package com.emis.EMIS.services;
import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.responseDTOs.AgentDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.OtherAdminsDTO;
import com.emis.EMIS.wrappers.responseDTOs.PartnerDTO;
import com.emis.EMIS.wrappers.responseDTOs.SchoolAdminDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EduVODAdminService {
    private final ModelMapper modelMapper;
    private final DataService dataService;
    private final Utilities utilities;

    //Other-System-Admins

    public ResponseDTO viewOtherAdmins() {
        List<OtherAdminEntity>otherAdminEntityList = dataService.viewAll();
        List<OtherAdminsDTO>otherAdminsDTOList = otherAdminEntityList.stream()
                .map(otherAdmin -> {
                    return OtherAdminsDTO.builder()
                            .employmentNo(otherAdmin.getEmploymentNo())
                            .officePhoneNo(otherAdmin.getOfficePhoneNo())
                            .employmentNo(otherAdmin.getEmploymentNo())
                            .department(otherAdmin.getDepartment())
                            .firstName(otherAdmin.getUserEntity().getFirstName())
                            .middleName(otherAdmin.getUserEntity().getMiddleName())
                            .lastName(otherAdmin.getUserEntity().getLastName())
                            .nationalId(otherAdmin.getUserEntity().getNationalId())
                            .email(otherAdmin.getUserEntity().getEmail())
                            .phoneNo(otherAdmin.getUserEntity().getPhoneNo())
                            .build();

                })
                .toList();
        return utilities.successResponse("Fetched active admins",otherAdminsDTOList);
    }


    public ResponseDTO singleAdmin(int id) {
        var otherAdmin = dataService.findByAdminId(id);
        var otherAdminsDTO =modelMapper.map(otherAdmin, OtherAdminsDTO.class);
        return utilities.successResponse("fetched a single admin",otherAdminsDTO);
    }

    public ResponseDTO updateAdminDetails(int id, OtherAdminsDTO otherAdminsDTO) {
        var otherAdmin = dataService.findByAdminId(id);
        otherAdmin.setDepartment(otherAdminsDTO.getDepartment());
        otherAdmin.setEmploymentNo(otherAdminsDTO.getEmploymentNo());
        otherAdmin.setOfficePhoneNo(otherAdminsDTO.getOfficePhoneNo());
        OtherAdminsDTO otherAdminsDTO1 = modelMapper.map(otherAdmin, OtherAdminsDTO.class);
        dataService.saveOtherAdmin(otherAdmin);
        return utilities.successResponse("updated admins details",otherAdmin);
    }

    public ResponseDTO deleteAdmin(int id) {
        var otherAdmin = dataService.findByAdminId(id);
        otherAdmin.setStatus(Status.DELETED);
        otherAdmin.getUserEntity().setStatus(Status.DELETED);
        dataService.saveOtherAdmin(otherAdmin);
        return utilities.successResponse("soft deleted an admin",null);
    }

    //School-Admins
    public ResponseDTO fetchActiveSchoolAdmins() {
        List<SchoolAdminInfoEntity>schoolAdminInfoEntities = dataService.fetchActiveSchoolAdmins();
        log.info("About to fetch active school admins {}",schoolAdminInfoEntities);
        List<SchoolAdminDTO>schoolAdminDTOList = schoolAdminInfoEntities.stream()
                .map(schoolAdminInfo -> {
                    return SchoolAdminDTO.builder()
                            .adminRole(schoolAdminInfo.getAdminRole())
                            .department(schoolAdminInfo.getDepartment())
                            .tscNumber(schoolAdminInfo.getTscNumber())
                            .officePhone(schoolAdminInfo.getOfficePhone())
                            .firstName(schoolAdminInfo.getUserEntity().getFirstName())
                            .middleName(schoolAdminInfo.getUserEntity().getMiddleName())
                            .lastName(schoolAdminInfo.getUserEntity().getLastName())
                            .nationalId(schoolAdminInfo.getUserEntity().getNationalId())
                            .email(schoolAdminInfo.getUserEntity().getEmail())
                            .phoneNo(schoolAdminInfo.getUserEntity().getPhoneNo())
                            .nationality(schoolAdminInfo.getUserEntity().getNationality())
                            .gender(schoolAdminInfo.getUserEntity().getGender())
                            .dateOfBirth(schoolAdminInfo.getUserEntity().getDateOfBirth())
                            .build();

                })
                .toList();
        return utilities.successResponse("Successfully fetched active school admins",schoolAdminDTOList);
    }


    public ResponseDTO fetchSchoolAdminById(int id) {
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        var schoolAdminDTO = SchoolAdminDTO.builder()
                .firstName(schoolAdminInfo.getUserEntity().getFirstName())
                .middleName(schoolAdminInfo.getUserEntity().getMiddleName())
                .lastName(schoolAdminInfo.getUserEntity().getLastName())
                .phoneNo(schoolAdminInfo.getUserEntity().getPhoneNo())
                .email(schoolAdminInfo.getUserEntity().getEmail())
                .nationalId(schoolAdminInfo.getUserEntity().getNationalId())
                .nationality(schoolAdminInfo.getUserEntity().getNationality())
                .dateOfBirth(schoolAdminInfo.getUserEntity().getDateOfBirth())
                .gender(schoolAdminInfo.getUserEntity().getGender())
                .officePhone(schoolAdminInfo.getOfficePhone())
                .adminRole(schoolAdminInfo.getAdminRole())
                .tscNumber(schoolAdminInfo.getTscNumber())
                .department(schoolAdminInfo.getDepartment())
                .build();
        return utilities.successResponse("fetched a school admin",schoolAdminDTO);

    }

    public ResponseDTO updateSchoolAdminDetails(int id, SchoolAdminDTO schoolAdminDTO) {
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        var user = schoolAdminInfo.getUserEntity();
        var userEntity = modelMapper.map(schoolAdminDTO, UserEntity.class);
        userEntity.setUserId(user.getUserId());
        dataService.saveUser(userEntity);
        dataService.saveSchoolAdmin(schoolAdminInfo);
        return utilities.successResponse("updated  school admin details",schoolAdminDTO);

    }
    public ResponseDTO deleteSchoolAdmin(int id){
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        schoolAdminInfo.setStatus(Status.DELETED);
        schoolAdminInfo.getUserEntity().setStatus(Status.DELETED);
        dataService.saveSchoolAdmin(schoolAdminInfo);
        return utilities.successResponse("deleted a school admin",null);
    }


    //Agents

    public ResponseDTO fetchActiveAgents() {
        List<AgentInfoEntity>agentInfoEntityList=dataService.fetchActiveAgents();
        log.info("Fetched agents from the db:{}",agentInfoEntityList);
        List<AgentDTO> agentDTOList = agentInfoEntityList.stream()
                .map(agentInfoEntity -> {
                  // return modelMapper.map(agentInfoEntity, AgentDTO.class);
                  return   AgentDTO.builder()
                            .agencyName(agentInfoEntity.getAgencyName())
                            .emergencyContact(agentInfoEntity.getEmergencyContact())
                            .firstName(agentInfoEntity.getUserEntity().getFirstName())
                            .middleName(agentInfoEntity.getUserEntity().getMiddleName())
                            .lastName(agentInfoEntity.getUserEntity().getLastName())
                            .nationalId(agentInfoEntity.getUserEntity().getNationalId())
                            .email(agentInfoEntity.getUserEntity().getEmail())
                            .phoneNo(agentInfoEntity.getUserEntity().getPhoneNo())
                            .build();
                })
                .toList();

        return utilities.successResponse("fetched all agents",agentDTOList);}

    public ResponseDTO fetchByAgentId(int id) {
        var agentEntity = dataService.findByAgentId(id);
        var agentDTO = modelMapper.map(agentEntity, AgentDTO.class);
        return utilities.successResponse("fetched an agent",agentDTO);
    }

    public ResponseDTO updateAgentByAgentId(int id, AgentDTO agentDTO) {
        var agentInfo = dataService.findByAgentId(id);
        agentInfo.setAgencyName(agentDTO.getAgencyName());
        agentInfo.setEmergencyContact(agentDTO.getEmergencyContact());
        AgentDTO agentDTO1 =modelMapper.map(agentInfo,AgentDTO.class);
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("Updated an agent",agentDTO1);
    }


    public ResponseDTO softDeleteAgent(int id) {
        var agentInfo = dataService.findByAgentId(id);
        agentInfo.setStatus(Status.DELETED);
        agentInfo.getUserEntity().setStatus(Status.DELETED);
        log.info("changed agent's status to deleted {}",agentInfo);
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("soft deleted agent",null);
    }


    public ResponseDTO viewActivePartners() {
        List<PartnerInfoEntity>partnerInfoEntityList = dataService.fetchActivePartners();
        List<PartnerDTO>partnerDTOList = partnerInfoEntityList.stream()
                .map(partnerInfoEntity ->{
                    return PartnerDTO .builder()
                            .firstName(partnerInfoEntity.getUserEntity().getFirstName())
                            .middleName(partnerInfoEntity.getUserEntity().getMiddleName())
                            .lastName(partnerInfoEntity.getUserEntity().getLastName())
                            .email(partnerInfoEntity.getUserEntity().getEmail())
                            .nationalId(partnerInfoEntity.getUserEntity().getNationalId())
                            .phoneNo(partnerInfoEntity.getUserEntity().getPhoneNo())
                            .businessEmail(partnerInfoEntity.getBusinessEmail())
                            .businessContact(partnerInfoEntity.getBusinessContact())
                            .businessEmail(partnerInfoEntity.getBusinessEmail())
                            .firmName(partnerInfoEntity.getFirmName())
                            .emergencyContact(partnerInfoEntity.getEmergencyContact())
                            .contractDetails(partnerInfoEntity.getContractDetails())
                            .agreementStartDate(partnerInfoEntity.getAgreementStartDate())
                            .agreementEndDate(partnerInfoEntity.getAgreementEndDate())
                            .build();
                })


                .toList();

return utilities.successResponse("Successfully fetched active partners",partnerDTOList);
    }


    public ResponseDTO fetchOne(int id) {
        var partnerInfo = dataService.findByPartnerId(id);
        PartnerDTO partnerDTO = modelMapper.map(partnerInfo, PartnerDTO.class);
        return utilities.successResponse("Successfully fetched a partner",partnerDTO);
    }


    public ResponseDTO updatePartnerDetails(int id, PartnerDTO partnerDTO) {
        var partnerInfo = dataService.findByPartnerId(id);
        partnerInfo.setFirmName(partnerDTO.getFirmName());
        partnerInfo.setEmergencyContact(partnerDTO.getEmergencyContact());
        partnerInfo.setBusinessContact(partnerDTO.getBusinessContact());
        partnerInfo.setContractDetails(partnerDTO.getContractDetails());
        partnerInfo.setBusinessEmail(partnerDTO.getBusinessEmail());
        partnerInfo.setAgreementStartDate(partnerDTO.getAgreementStartDate());
        partnerInfo.setAgreementEndDate(partnerDTO.getAgreementEndDate());
        PartnerDTO partnerDTO1 = modelMapper.map(partnerInfo, PartnerDTO.class);
        dataService.savePartner(partnerInfo);
        return utilities.successResponse("Successfully updated a partners details",partnerDTO1);

    }

    public ResponseDTO deletePartner(int id) {
        var partnerInfo = dataService.findByPartnerId(id);
        partnerInfo.setStatus(Status.DELETED);
        partnerInfo.getUserEntity().setStatus(Status.DELETED);
        dataService.savePartner(partnerInfo);
        return utilities.successResponse("deleted a partner",null);

    }

}
