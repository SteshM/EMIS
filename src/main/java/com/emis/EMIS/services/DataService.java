package com.emis.EMIS.services;

import com.emis.EMIS.models.OTPEntity;
import com.emis.EMIS.models.ProfileEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.repositories.OTPRepo;
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
    private final OTPRepo otpRepo;

    public UserEntity saveUser(UserEntity userEntity) {
        log.info("Just about to save a user :: {}",userEntity);
        return userRepo.save(userEntity);
    }

    public Optional<UserEntity> findByEmail(String username) {
        return userRepo.findByEmail(username);
    }
    public void saveOTP(OTPEntity otpEntity){
        otpRepo.save(otpEntity);
    }
    public OTPEntity findOTPByUserId(int userId){
        return otpRepo.findByUserEntityUserId(userId).orElseThrow();
    }
    public UserEntity findByUserId(int userId){
        return userRepo.findByUserId(userId);
    }
    public UserEntity savePassword(UserEntity userEntity){
        return userRepo.save(userEntity);
    }

}