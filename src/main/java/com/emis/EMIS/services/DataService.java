package com.emis.EMIS.services;

import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataService {
    private final UserRepo userRepo;

    public UserEntity saveUser(UserEntity userEntity){
        return userRepo.save(userEntity);
    }

}
