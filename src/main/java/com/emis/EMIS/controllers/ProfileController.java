package com.emis.EMIS.controllers;
import com.emis.EMIS.services.ProfileService;
import com.emis.EMIS.wrappers.requestDTOs.ProfileDto;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/create-profile")
    @PreAuthorize("hasAnyRole(SuperAdmin) and hasAnyAuthority(CREATE_PROFILES)")
    public ResponseDTO createProfile(@RequestBody ProfileDto profileDto){
        return profileService.createProfile(profileDto);
    }

    @GetMapping("/profiles")
    @PreAuthorize("hasAnyRole(SuperAdmin) and hasAnyAuthority(VIEW_PROFILES)")
    public ResponseDTO fetchProfiles(){
        return profileService.fetchAll();
    }
    @GetMapping("/profile/{profile}")
    public ResponseDTO fetchOne(String profile){
        return profileService.fetchByProfile(profile);
    }

    @GetMapping("/all-users")
    public ResponseDTO fetchAllUsersWithProfiles(){
        return profileService.fetchUsersWIthProfiles();
    }






}
