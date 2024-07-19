package com.emis.EMIS.wrappers.requestDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Setter
@Getter
public class SupportDocDTO {
//    private int id;
//    private int supportId;
    private String name;
    private String description;
    private int menuCodeId;
    private int documentTypeId;
    private int schoolId;
    private String supportDoc;
}
