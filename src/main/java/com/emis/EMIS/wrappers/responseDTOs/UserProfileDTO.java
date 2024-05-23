package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileDTO {
   private String firstName;
   private String middleName;
   private String lastName;
   private String email;
   private int phoneNo;
   private int nationalId;
}
