package com.emis.EMIS.utils;

import com.emis.EMIS.models.AuditTrailEntity;
import com.emis.EMIS.services.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditTrailUtil {
    private final DataService dataService;

    public void createAuditTrail( String action,String actionDescription,int isSuccessful){
       AuditTrailEntity auditTrail = AuditTrailEntity.builder()
               .action(action)
               .actionDescription(actionDescription)
               .isSuccessful(isSuccessful)
//               .username(username)
               .build();
        dataService.saveAuditTrail(auditTrail);

    }



}
