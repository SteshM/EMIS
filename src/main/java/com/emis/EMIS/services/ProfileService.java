package com.emis.EMIS.services;

import com.emis.EMIS.models.AgentInfoEntity;
import com.emis.EMIS.models.ProfileEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.ProfileDto;
import com.emis.EMIS.wrappers.responseDTOs.ProfileUserResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import com.emis.EMIS.wrappers.responseDTOs.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProfileService {
    private final DataService dataService;
    private final ModelMapper modelMapper;
    private final Utilities utilities;

    public ResponseDTO createProfile(ProfileDto profileDto) {
        var profile = modelMapper.map(profileDto, ProfileEntity.class);
        if(dataService.findByProfile(profileDto.getProfile()) == null){
            dataService.saveProfile(profile);
            return utilities.successResponse("created profile", profile);
        }
        return utilities.failedResponse(400, "profile exists", null);
    }


    public ResponseDTO fetchAll() {
        List<ProfileEntity> profiles = dataService.fetchAll();
        return utilities.successResponse("fetched all profiles",profiles);
    }


    public ResponseDTO fetchByProfile(String profile) {
        var profileEntity = dataService.findByProfile(profile);
        return utilities.successResponse("fetched profile",profileEntity);
    }

    public ResponseDTO fetchUsersWIthProfiles() {
        List<UserEntity>userEntities = dataService. findAllUsersWithProfiles();
        List<ProfileUserResponseDTO>userResponseDTOS = userEntities.stream()
                .map(userEntity -> {
                    return ProfileUserResponseDTO.builder()
                            .userId(userEntity.getUserId())
                            .firstName(userEntity.getFirstName())
                            .middleName(userEntity.getMiddleName())
                            .lastName(userEntity.getLastName())
                            .email(userEntity.getEmail())
                            .phoneNo(userEntity.getPhoneNo())
                            .gender(userEntity.getGender())
                            .nationality(userEntity.getNationality() == null?"":userEntity.getNationality())
                            .nationalId(userEntity.getNationalId() == null?"":userEntity.getNationalId())
                            .dateOfBirth(userEntity.getDateOfBirth() == null?"":userEntity.getDateOfBirth())
                            .agencyName(userEntity.getAgentInfoEntities().getFirst().getAgencyName()  == null?"":userEntity.getAgentInfoEntities().getFirst().getAgencyName())
                            .emergencyContact(userEntity.getAgentInfoEntities()  == null?"":userEntity.getAgentInfoEntities().getFirst().getEmergencyContact())
                            .resource(userEntity.getPartnerInfoEntities().getFirst().getEducationalResource()  == null?"":userEntity.getPartnerInfoEntities().getFirst().getEducationalResource().getResource())
                            .firmName(userEntity.getPartnerInfoEntities()  == null?"":userEntity.getPartnerInfoEntities().getFirst().getFirmName())
                            .businessContact(userEntity.getPartnerInfoEntities()  == null?"":userEntity.getPartnerInfoEntities().getFirst().getBusinessContact())
                            .businessEmail(userEntity.getPartnerInfoEntities() == null?"":userEntity.getPartnerInfoEntities().getFirst().getBusinessEmail())
                            .agreementEndDate(userEntity.getPartnerInfoEntities().getFirst().getAgreementEndDate())
                            .agreementStartDate(userEntity.getPartnerInfoEntities().getFirst().getAgreementStartDate())
                            .emergencyContact(userEntity.getPartnerInfoEntities() == null?"":userEntity.getPartnerInfoEntities().getFirst().getEmergencyContact())
                            .registrationNo(userEntity.getStudentEntities()  == null?"":userEntity.getStudentEntities().getFirst().getRegistrationNo())
                            .department(userEntity.getSchoolAdminInfoEntities()  == null?"":userEntity.getSchoolAdminInfoEntities().getFirst().getDepartment())
                            .adminRole(userEntity.getSchoolAdminInfoEntities()  == null?"":userEntity.getSchoolAdminInfoEntities().getFirst().getAdminRole())
                            .officePhone(userEntity.getSchoolAdminInfoEntities()  == null?"":userEntity.getSchoolAdminInfoEntities().getFirst().getOfficePhone())
                            .tscNo(userEntity.getSchoolAdminInfoEntities() == null?"":userEntity.getSchoolAdminInfoEntities().getFirst().getTscNumber())
                            .department(userEntity.getSystemAdminEntities()  == null?"":userEntity.getSystemAdminEntities().getFirst().getDepartment())
                            .officePhoneNo(userEntity.getSystemAdminEntities()  == null?"":userEntity.getSystemAdminEntities().getFirst().getOfficePhoneNo())
                            .employmentNo(userEntity.getSystemAdminEntities()  == null?"":userEntity.getSystemAdminEntities().getFirst().getEmploymentNo())
                            .tscNo(userEntity.getTeacherEntities()  == null?"":userEntity.getTeacherEntities().getFirst().getTscNo())
                            .yearsOfExperience(userEntity.getTeacherEntities().getFirst().getYearsOfExperience())
                            .profile(userEntity.getProfiles() == null?"": userEntity.getProfiles().getFirst().getProfile())
                            .school(userEntity.getTeacherEntities() == null?"":userEntity.getTeacherEntities().getFirst().getSchool().getSchoolName())
                            .school(userEntity.getStudentEntities()  == null?"":userEntity.getStudentEntities().getFirst().getSchools().getSchoolName())
                            .build();

                })
                .toList();
        return utilities.successResponse("fetched all users with their profiles",userResponseDTOS);


    }
}
