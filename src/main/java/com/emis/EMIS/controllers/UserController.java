package com.emis.EMIS.controllers;

import com.emis.EMIS.services.OTPService;
import com.emis.EMIS.services.UserService;
import com.emis.EMIS.utils.JwtUtil;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final OTPService otpService;

    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PostMapping("/all/create-profile")
    public ResponseDTO createProfile(ProfileDto profileDto){
        return userService.createProfile(profileDto);
    }

    @PostMapping("/all/register")
    public ResponseDTO register(@RequestBody UserDTO userDTO){
        log.info("Register request received from the customer::{}",userDTO);
        return userService.registerUser(userDTO);
    }

    @PostMapping("/all/activateAcc")
    public ResponseDTO verifyOtp(@RequestBody ActivateAccDTO activateAccDTO){
        return userService.activateAccount(activateAccDTO);
    }

    @GetMapping("/all/regenerate-otp/{userId}")
    public ResponseDTO regenerateOTP(@PathVariable int userId){
        return otpService.regenerateOtp(userId);
    }

    @PostMapping("/all/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        Map<String, String> objectMap = new HashMap<>();
        try {
            log.info("userDTO:: {}", userDTO);
            userService.loadUserByUsername(userDTO.getEmail());
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
    @PostMapping("/all/forgot-password/{email}")
    public ResponseDTO forgotPassword(@PathVariable String email){
        return otpService.forgotPassword(email);
    }

    @PostMapping("/v1/admsdm/add-authority")
    public ResponseDTO addAuthority(@RequestBody AddAuthDto addAuthDto){
        return userService.addAuthority(addAuthDto);
    }


}

