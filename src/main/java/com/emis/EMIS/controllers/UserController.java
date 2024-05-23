package com.emis.EMIS.controllers;

import com.emis.EMIS.services.UserService;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PostMapping("/all/register")
    public ResponseDTO register(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }
}
//
//    @PostMapping("/all/login")
//    public ResponseDTO login(@RequestBody UserDTO userDTO){
//        return userService.login(userDTO);
//    }
//}
