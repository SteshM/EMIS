package com.emis.EMIS.services;

import com.emis.EMIS.models.ProfileEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.repositories.ProfileRepo;
import com.emis.EMIS.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataService {
    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;

    public UserEntity saveUser(UserEntity userEntity){
        return userRepo.save(userEntity);
    }
    public UserEntity getUser(String email ){
        return userRepo.findByEmail(email);
    }

    public ProfileEntity saveProfile(ProfileEntity profileEntity){
        return profileRepo.save(profileEntity);
    }

}
