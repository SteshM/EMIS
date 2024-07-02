package com.emis.EMIS.wrappers.requestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SubmitSchoolDTO {
    private int schoolId;
    private String name;
    private  String remarks;
    private Boolean systemRole;
    private Long profileId;
    private List<Long> roleList;
}
