package com.emis.EMIS.wrappers.responseDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileDTO {
   private int userId;
   private String firstName;
   private String middleName;
   private String lastName;
   private String email;
   private String phoneNo;
   private String nationalId;
}
