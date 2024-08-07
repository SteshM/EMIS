package com.emis.EMIS.services;
import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.*;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.*;
import com.emis.EMIS.wrappers.responseDTOs.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseDTO viewSystemAdmins() throws JsonProcessingException {
        List<SystemAdminEntity>systemAdminEntityList = dataService.viewAll();
        List<SystemAdminsDTO>otherAdminsDTOList = systemAdminEntityList.stream()
                .map(systemAdmin -> {
                  return modelMapper.map(systemAdmin,SystemAdminsDTO.class);

                })
                .toList();
        log.info("Fetched  all System Admins' Details:{}", new ObjectMapper().writeValueAsString(systemAdminEntityList));
        return utilities.successResponse("Fetched active admins",otherAdminsDTOList);
    }

    public ResponseDTO singleAdmin(int id) throws JsonProcessingException {
        var systemAdmin = dataService.findByAdminId(id);
        var systemAdminsDTO =modelMapper.map(systemAdmin,SystemAdminsDTO.class);
        log.info("Fetched student Details:{}", new ObjectMapper().writeValueAsString(systemAdmin));
        return utilities.successResponse("fetched a single admin",systemAdminsDTO);
    }

    public ResponseDTO updateAdminDetails(int id, UpdateAdminDTO updateAdminDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var systemAdmin = dataService.findByAdminId(id);
        log.info("Fetched an admin:{}", objectMapper.writeValueAsString(systemAdmin));
        systemAdmin.getUserEntity().setFirstName(updateAdminDTO.getFirstName());
        systemAdmin.getUserEntity().setMiddleName(updateAdminDTO.getMiddleName());
        systemAdmin.getUserEntity().setLastName(updateAdminDTO.getLastName());
        systemAdmin.getUserEntity().setEmail(updateAdminDTO.getEmail());
        systemAdmin.getUserEntity().setPhoneNo(updateAdminDTO.getPhoneNo());
        systemAdmin.getUserEntity().setNationalId(updateAdminDTO.getNationalId());
        systemAdmin.setDepartment(updateAdminDTO.getDepartment());
        systemAdmin.setEmploymentNo(updateAdminDTO.getEmploymentNo());
        systemAdmin.setOfficePhoneNo(updateAdminDTO.getOfficePhoneNo());
        log.info("Updated admins Details. About to save:{}", objectMapper.writeValueAsString(systemAdmin));
        dataService.saveSystemAdmin(systemAdmin);
        return utilities.successResponse("updated admins details",updateAdminDTO);
    }

    public ResponseDTO deleteAdmin(int id) {
        var systemAdmin = dataService.findByAdminId(id);
        systemAdmin.setStatus(Status.DELETED);
        systemAdmin.getUserEntity().setStatus(Status.DELETED);
        dataService.saveSystemAdmin(systemAdmin);
        return utilities.successResponse("soft deleted an admin",null);
    }

    //School-Admins
    public ResponseDTO fetchActiveSchoolAdmins() throws JsonProcessingException {
        List<SchoolAdminInfoEntity>schoolAdminInfoEntities = dataService.fetchActiveSchoolAdmins();
        log.info("About to fetch active school admins {}",schoolAdminInfoEntities);
        List<SchoolAdminDTO>schoolAdminDTOList = schoolAdminInfoEntities.stream()
                .map(schoolAdminInfo -> {
                    return modelMapper.map(schoolAdminInfo,SchoolAdminDTO.class);
                })
                .toList();
        log.info("Fetched  all School admin Details:{}", new ObjectMapper().writeValueAsString(schoolAdminInfoEntities));
        return utilities.successResponse("Successfully fetched active school admins",schoolAdminDTOList);
    }


    public ResponseDTO fetchSchoolAdminById(int id) throws JsonProcessingException {
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        log.info("Fetched school admin Details:{}", new ObjectMapper().writeValueAsString(schoolAdminInfo));
        var schoolAdminDTO = modelMapper.map(schoolAdminInfo, SchoolAdminDTO.class);
        return utilities.successResponse("fetched a school admin",schoolAdminDTO);

    }

    public ResponseDTO updateSchoolAdminDetails(int id, UpdateSchAdminDTO updateSchAdminDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        log.info("about to fetch  an admin{}",objectMapper.writeValueAsString(schoolAdminInfo));
        schoolAdminInfo.getUserEntity().setFirstName(updateSchAdminDTO.getFirstName());
        schoolAdminInfo.getUserEntity().setMiddleName(updateSchAdminDTO.getMiddleName());
        schoolAdminInfo.getUserEntity().setLastName(updateSchAdminDTO.getLastName());
        schoolAdminInfo.getUserEntity().setEmail(updateSchAdminDTO.getEmail());
        schoolAdminInfo.getUserEntity().setPhoneNo(updateSchAdminDTO.getPhoneNo());
        schoolAdminInfo.getUserEntity().setNationalId(updateSchAdminDTO.getNationalId());
        schoolAdminInfo.getUserEntity().setNationality(updateSchAdminDTO.getNationality());
        schoolAdminInfo.getUserEntity().setGender(updateSchAdminDTO.getGender());
        schoolAdminInfo.getUserEntity().setDateOfBirth(updateSchAdminDTO.getDateOfBirth());
        schoolAdminInfo.setAdminRole(updateSchAdminDTO.getAdminRole());
        schoolAdminInfo.setDepartment(updateSchAdminDTO.getDepartment());
        schoolAdminInfo.setTscNumber(updateSchAdminDTO.getTscNumber());
        schoolAdminInfo.setOfficePhone(updateSchAdminDTO.getOfficePhone());
        log.info("Updated  school admins Details. About to save:{}", objectMapper.writeValueAsString(schoolAdminInfo));
        dataService.saveSchoolAdmin(schoolAdminInfo);
        return utilities.successResponse("updated  school admin details",updateSchAdminDTO);

    }

    public ResponseDTO deleteSchoolAdmin(int id){
        var schoolAdminInfo = dataService.findBySchoolAdminId(id);
        schoolAdminInfo.setStatus(Status.DELETED);
        schoolAdminInfo.getUserEntity().setStatus(Status.DELETED);
        dataService.saveSchoolAdmin(schoolAdminInfo);
        return utilities.successResponse("deleted a school admin",null);
    }


    //Agents

    public ResponseDTO fetchActiveAgents() throws JsonProcessingException {
        List<AgentInfoEntity>agentInfoEntityList=dataService.fetchActiveAgents();
        log.info("Fetched agents from the db:{}",agentInfoEntityList);
        List<AgentDTO> agentDTOList = agentInfoEntityList.stream()
                .map(agentInfoEntity -> {
                    return modelMapper.map(agentInfoEntity, AgentDTO.class);

                })
                .toList();
    log.info("About to fetch all active agents' Details:{}", new ObjectMapper().writeValueAsString(agentInfoEntityList));
    return utilities.successResponse("fetched all agents",agentDTOList);}


    /**
     * fetching a single agent from the db
     * @param id the agent id
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO fetchByAgentId(int id) throws JsonProcessingException {
        var agentEntity = dataService.findByAgentId(id);
        log.info("Fetched agent Details:{}", new ObjectMapper().writeValueAsString(agentEntity));
        var agentDTO = modelMapper.map(agentEntity, AgentDTO.class);
        return utilities.successResponse("fetched an agent",agentDTO);
    }


    /**
     * fetching an agent from te bd ,updating and saving the details
     * @param id the agent id
     * @return the response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO updateAgentByAgentId(int id, UpdateAgentDTO updateAgentDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var agentInfo = dataService.findByAgentId(id);
        log.info("Fetching agent's details from the db {}",objectMapper.writeValueAsString(agentInfo));
        agentInfo.getUserEntity().setFirstName(updateAgentDTO.getFirstName());
        agentInfo.getUserEntity().setMiddleName(updateAgentDTO.getMiddleName());
        agentInfo.getUserEntity().setLastName(updateAgentDTO.getLastName());
        agentInfo.getUserEntity().setEmail(updateAgentDTO.getEmail());
        agentInfo.getUserEntity().setPhoneNo(updateAgentDTO.getPhoneNo());
        agentInfo.getUserEntity().setNationalId(updateAgentDTO.getNationalId());
        agentInfo.setEmergencyContact(updateAgentDTO.getEmergencyContact());
        agentInfo.setAgencyName(updateAgentDTO.getAgencyName());
        log.info("Updated agent Details. About to save:{}", objectMapper.writeValueAsString(agentInfo));
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("Updated an agent",updateAgentDTO);
    }

    public ResponseDTO softDeleteAgent(int id) {
        var agentInfo = dataService.findByAgentId(id);
        agentInfo.setStatus(Status.DELETED);
        agentInfo.getUserEntity().setStatus(Status.DELETED);
        log.info("changed agent's status to deleted {}",agentInfo);
        dataService.saveAgent(agentInfo);
        return utilities.successResponse("soft deleted agent",null);
    }


    //Partners

    /**
     * Fetching active partners from the db
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO viewActivePartners() throws JsonProcessingException {
        List<PartnerInfoEntity>partnerInfoEntityList = dataService.fetchActivePartners();
        log.info("about to fetch active partners from te db : {}",partnerInfoEntityList);
        List<PartnerDTO>partnerDTOList = partnerInfoEntityList.stream()
                .map(partnerInfoEntity ->{
                    return PartnerDTO.builder()
                            .partnerId(partnerInfoEntity.getPartnerId())
                            .resourceId(partnerInfoEntity.getEducationalResource() == null?0:partnerInfoEntity.getEducationalResource().getResourceId())
                            .resource(partnerInfoEntity.getEducationalResource() == null?"":partnerInfoEntity.getEducationalResource().getResource())
                            .firstName(partnerInfoEntity.getUserEntity().getFirstName())
                            .middleName(partnerInfoEntity.getUserEntity().getMiddleName())
                            .lastName(partnerInfoEntity.getUserEntity().getLastName())
                            .email(partnerInfoEntity.getUserEntity().getEmail())
                            .nationalId(partnerInfoEntity.getUserEntity().getNationalId())
                            .phoneNo(partnerInfoEntity.getUserEntity().getPhoneNo())
                            .firmName(partnerInfoEntity.getFirmName())
                            .emergencyContact(partnerInfoEntity.getEmergencyContact())
                            .agreementStartDate(partnerInfoEntity.getAgreementStartDate())
                            .agreementEndDate(partnerInfoEntity.getAgreementEndDate())
                            .businessContact(partnerInfoEntity.getBusinessContact())
                            .businessEmail(partnerInfoEntity.getBusinessEmail())
                            .build();
                })
                .toList();
        log.info("Fetching all active partners' Details:{}", new ObjectMapper().writeValueAsString(partnerInfoEntityList));
        return utilities.successResponse("Successfully fetched active partners",partnerDTOList);
    }


    /**
     * Fetching a single partner from the db
     * @param id partner id
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO fetchOne(int id) throws JsonProcessingException {
        var partnerInfo = dataService.findByPartnerId(id);
        log.info("Fetching a partner's Details:{}", new ObjectMapper().writeValueAsString(partnerInfo));
        var partnerDTO = modelMapper.map(partnerInfo, PartnerDTO.class);
        return utilities.successResponse("Successfully fetched a partner",partnerDTO);
    }


    /**
     * Fetching a partner's details from the db,updating and saving
     * @param id the partner id
     * @return response dto
     * @throws JsonProcessingException the exception
     */
    public ResponseDTO updatePartnerDetails(int id, UpdatePartnerDTO updatePartnerDTO) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var partnerInfo = dataService.findByPartnerId(id);
        log.info("About to fetch a partner's details{}",objectMapper.writeValueAsString(partnerInfo));
        partnerInfo.getUserEntity().setFirstName(updatePartnerDTO.getFirstName());
        partnerInfo.getUserEntity().setMiddleName(updatePartnerDTO.getMiddleName());
        partnerInfo.getUserEntity().setLastName(updatePartnerDTO.getLastName());
        partnerInfo.getUserEntity().setEmail(updatePartnerDTO.getEmail());
        partnerInfo.getUserEntity().setPhoneNo(updatePartnerDTO.getPhoneNo());
        partnerInfo.getUserEntity().setNationalId(updatePartnerDTO.getNationalId());
        partnerInfo.getEducationalResource().setResource(updatePartnerDTO.getResource());
        partnerInfo.setAgreementStartDate(updatePartnerDTO.getAgreementStartDate());
        partnerInfo.setAgreementEndDate(updatePartnerDTO.getAgreementEndDate());
        partnerInfo.setBusinessEmail(updatePartnerDTO.getBusinessEmail());
        partnerInfo.setBusinessContact(updatePartnerDTO.getBusinessContact());
        partnerInfo.setEmergencyContact(updatePartnerDTO.getEmergencyContact());
        partnerInfo.setFirmName(updatePartnerDTO.getFirmName());
        log.info("Updated a Partner's Details. About to save:{}", objectMapper.writeValueAsString(partnerInfo));
        dataService.savePartner(partnerInfo);
        return utilities.successResponse("Successfully updated a partners details",updatePartnerDTO);

    }

    /**
     * soft deleting a partner's details
     * @param id partner id
     * @return response dto
     */
    public ResponseDTO deletePartner(int id) {
        var partnerInfo = dataService.findByPartnerId(id);
        partnerInfo.setStatus(Status.DELETED);
        partnerInfo.getUserEntity().setStatus(Status.DELETED);
        dataService.savePartner(partnerInfo);
        return utilities.successResponse("deleted a partner",null);

    }

    public ResponseDTO createResource(ResourceDTO resourceDTO) throws JsonProcessingException {
        var educationalResource = modelMapper.map(resourceDTO,EducationalResourceEntity.class);
        log.info("about to save a resource to the db : {}",new ObjectMapper().writeValueAsString(educationalResource));
        dataService.saveResource(educationalResource);
        return utilities.successResponse("Successfully added a resource",null);
    }

    public ResponseDTO AllResources(){
        List<EducationalResourceEntity>educationalResourceEntityList = dataService.fetchAllResources();
        List<ResourceResDTO>resDTOS = educationalResourceEntityList.stream()
                .map(educationalResourceEntity -> {
                    return ResourceResDTO.builder()
                            .resourceId(educationalResourceEntity.getResourceId())
                            .resource(educationalResourceEntity.getResource())
                            .build();
                })
                .toList();
        return utilities.successResponse("fetched all resources fro the db",resDTOS);
    }

    public ResponseDTO editResource(int id,ResourceDTO resourceDTO){
        EducationalResourceEntity educationalResource = dataService.findByResourceId(id);
        educationalResource.setResource(resourceDTO.getResource());
        dataService.saveResource(educationalResource);
        return utilities.successResponse("updated resource",resourceDTO);
    }

}
