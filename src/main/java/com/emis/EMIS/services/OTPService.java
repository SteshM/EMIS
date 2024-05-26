package com.emis.EMIS.services;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.models.OTPEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.RandomGenerator;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.requestDTOs.OtpDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class OTPService {
    private final DataService dataService;
    private final UserConfigs userConfigs;
    private final Utilities utilities;
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void generateOTP(UserEntity userEntity){
        //generate a random number
        String otp = RandomGenerator.generateChars(userConfigs.getOtpLength());
        //insert to the db
        OTPEntity otpEntity =new OTPEntity();
        otpEntity.setOtp(utilities.encoder(otp));
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

    public boolean verifyOtp(OtpDTO otpDTO) {
        try {
            var otpEntity = dataService.findOTPByUserId(otpDTO.getUserId());
            log.info("OTP Entity Returned:{}", otpEntity);
            String encodedOtp = utilities.encoder(otpDTO.getOtp());
            log.info("Encoded OTP:{}", encodedOtp);
            return Objects.equals(encodedOtp, otpEntity.getOtp());
        } catch (Exception e){
            log.error("Caught an exception:",e);
            return false;
        }

    }

}
