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
    private final ModelMapper modelMapper;
    private final DataService dataService;
    private final Utilities utilities;

    public ResponseDTO createAuditTrail(@RequestBody AuditTrailDTO auditTrailDTO){
       AuditTrailEntity auditTrail = modelMapper.map(auditTrailDTO,AuditTrailEntity.class);
       var savedAuditTrail = dataService.saveAuditTrail(auditTrail);
       var auditTrailResDTO = modelMapper.map(savedAuditTrail, AuditTrailResDTO.class);
       return utilities.successResponse("Saved an audit trail",auditTrailResDTO);

    }
}
