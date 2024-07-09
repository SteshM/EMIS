package com.emis.EMIS.utils;

import com.emis.EMIS.models.AuditTrailEntity;
import com.emis.EMIS.services.DataService;
import com.emis.EMIS.wrappers.requestDTOs.AuditTrailDTO;
import com.emis.EMIS.wrappers.responseDTOs.AuditTrailResDTO;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RequiredArgsConstructor
public class AuditTrailUtil {
    private final DataService dataService;

    public void createAuditTrail( String action,String actionDescription,boolean isSuccessful){
       AuditTrailEntity auditTrail = AuditTrailEntity.builder()
               .action(action)
               .actionDescription(actionDescription)
               .isSuccessful(isSuccessful)
               .build();
        dataService.saveAuditTrail(auditTrail);

    }



}
