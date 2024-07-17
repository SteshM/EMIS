package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApproveSchoolDTO {
    private int schoolId;
    private String name;
    private String remarks;
}
