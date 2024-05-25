package com.emis.EMIS.services;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.models.OTPEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.RandomGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OTPService {
    private final DataService dataService;
    private final UserConfigs userConfigs;
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public void generateOTP(UserEntity userEntity){
        //generate a random number
        String otp = RandomGenerator.generateChars(userConfigs.getOtpLength());
        //insert to the db
        OTPEntity otpEntity =new OTPEntity();
        otpEntity.setOtp(passwordEncoder().encode(otp));
        otpEntity.setUserEntity(userEntity);
        dataService.saveOTP(otpEntity);

        //send out the otp
        this.sendOTP(otp,userEntity);


    }
    private void sendOTP( String otp,UserEntity userEntity){
        log.info("OTP:{}", otp);
        //Send via Email
        //Send via SMS
    }
}
