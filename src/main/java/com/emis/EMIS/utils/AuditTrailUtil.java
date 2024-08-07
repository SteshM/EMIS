package com.emis.EMIS.utils;

import com.emis.EMIS.models.AuditTrailEntity;
import com.emis.EMIS.services.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AuditTrailUtil {
    private final DataService dataService;

    public void createAuditTrail( String action,String actionDescription,int isSuccessful,String username){
       AuditTrailEntity auditTrail = AuditTrailEntity.builder()
               .action(action)
               .actionDescription(actionDescription)
               .isSuccessful(isSuccessful)
               .username(username)
               .performedOn(Instant.now())
               .createdBy(username)
               .build();
        dataService.saveAuditTrail(auditTrail);

    }



}
