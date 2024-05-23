package com.emis.EMIS.controllers;

import com.emis.EMIS.services.UserService;
import com.emis.EMIS.utils.JwtUtil;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.UserDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    private  final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PostMapping("/all/register")
    public ResponseDTO register(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }


    @PostMapping("/all/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        Map<String, String> objectMap = new HashMap<>();
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
            if (authenticate.isAuthenticated()) {
                String jwtToken =  jwtUtil.generateToken(userDTO.getEmail());
                objectMap.put("jwtToken", jwtToken);
                responseDTO.setStatusMessage("Authenticated successfully");
                responseDTO.setResult(objectMap);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setStatusMessage("Invalid access");
                return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.error("Exception occurred ", ex);
            responseDTO.setStatusMessage(ex.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN);
        }

    }
}

