package com.emis.EMIS.services;

import com.emis.EMIS.models.ProfileEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.repositories.ProfileRepo;
import com.emis.EMIS.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataService {
    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;

    public UserEntity saveUser(UserEntity userEntity){
        return userRepo.save(userEntity);
    }

    public Optional<UserEntity> findByEmail(String username)
    {return userRepo.findByEmail(username);
    }
//    public ProfileEntity getProfile(String username ){
//        return profileRepo.findByUsername(username);
    }
//
//    public UserEntity getUser(String email ){
//        return userRepo.findByEmail(email);
//    }
//
//    public ProfileEntity saveProfile(ProfileEntity profileEntity){
//        return profileRepo.save(profileEntity);
//    }
//
//}
