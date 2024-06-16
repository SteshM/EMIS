package com.emis.EMIS.configs;

import com.emis.EMIS.models.*;
import com.emis.EMIS.wrappers.responseDTOs.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PropertyMap<TeacherEntity, TeacherDTO> teacherEntityTeacherDTOPropertyMap = new PropertyMap<TeacherEntity, TeacherDTO>() {
            protected void configure() {
                map().setFirstName(source.getUser().getFirstName());
                map().setMiddleName(source.getUser().getMiddleName());
                map().setLastName(source.getUser().getLastName());
                map().setEmail(source.getUser().getEmail());
                map().setPhoneNo(source.getUser().getPhoneNo());
                map().setNationalId(source.getUser().getNationalId());
                map().setGender(source.getUser().getGender());
                map().setNationality(source.getUser().getNationality());
                map().setDateOfBirth(source.getUser().getDateOfBirth());
            }
        };

        PropertyMap<StudentEntity, StudentDTO> studentEntityStudentDTOPropertyMap = new PropertyMap<StudentEntity, StudentDTO>() {
            protected void configure() {
                map().setFirstName(source.getUser().getFirstName());
                map().setMiddleName(source.getUser().getMiddleName());
                map().setLastName(source.getUser().getLastName());
                map().setEmail(source.getUser().getEmail());
                map().setGender(source.getUser().getGender());
                map().setNationality(source.getUser().getNationality());
                map().setDateOfBirth(source.getUser().getDateOfBirth());
            }
        };
            PropertyMap<AgentInfoEntity, AgentDTO>agentInfoEntityAgentDTOPropertyMap = new PropertyMap<AgentInfoEntity, AgentDTO>() {
                protected void  configure(){
                map().setFirstName(source.getUserEntity().getFirstName());
                map().setMiddleName(source.getUserEntity().getMiddleName());
                map().setLastName(source.getUserEntity().getLastName());
                map().setEmail(source.getUserEntity().getEmail());
                map().setPhoneNo(source.getUserEntity().getPhoneNo());
                map().setNationalId(source.getUserEntity().getNationalId());
                }
            };
            PropertyMap<PartnerInfoEntity, PartnerDTO>partnerInfoEntityPartnerDTOPropertyMap = new PropertyMap<PartnerInfoEntity, PartnerDTO>() {
                protected void configure() {
                    map().setFirstName(source.getUserEntity().getFirstName());
                    map().setMiddleName(source.getUserEntity().getMiddleName());
                    map().setLastName(source.getUserEntity().getLastName());
                    map().setEmail(source.getUserEntity().getEmail());
                    map().setPhoneNo(source.getUserEntity().getPhoneNo());
                    map().setNationalId(source.getUserEntity().getNationalId());

                }
            };
            PropertyMap<SystemAdminEntity, SystemAdminsDTO>systemAdminEntitySystemAdminsDTOPropertyMap=new PropertyMap<SystemAdminEntity, SystemAdminsDTO>() {
                protected void configure() {
                    map().setFirstName(source.getUserEntity().getFirstName());
                    map().setMiddleName(source.getUserEntity().getMiddleName());
                    map().setLastName(source.getUserEntity().getLastName());
                    map().setEmail(source.getUserEntity().getEmail());
                    map().setPhoneNo(source.getUserEntity().getPhoneNo());
                    map().setNationalId(source.getUserEntity().getNationalId());

                }
            };

        modelMapper.addMappings(teacherEntityTeacherDTOPropertyMap);
        modelMapper.addMappings(studentEntityStudentDTOPropertyMap);
        modelMapper.addMappings(agentInfoEntityAgentDTOPropertyMap);
        modelMapper.addMappings(partnerInfoEntityPartnerDTOPropertyMap);
        modelMapper.addMappings(systemAdminEntitySystemAdminsDTOPropertyMap);
        return modelMapper;
    }


}
