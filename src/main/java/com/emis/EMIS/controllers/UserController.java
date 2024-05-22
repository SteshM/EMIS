package com.emis.EMIS.controllers;

import com.emis.EMIS.services.UserService;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/all/register")
    public ResponseDTO register(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }
}
