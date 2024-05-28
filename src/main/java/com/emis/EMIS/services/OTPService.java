package com.emis.EMIS.services;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.models.OTPEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.Exchanger;
import com.emis.EMIS.utils.RandomGenerator;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.ResponseDTO;
import com.emis.EMIS.wrappers.requestDTOs.ForgotPasswordDTO;
import com.emis.EMIS.wrappers.requestDTOs.OtpDTO;
import com.emis.EMIS.wrappers.requestDTOs.PasswordChangeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

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
    private final Exchanger exchanger;
    private final RandomGenerator randomGenerator;


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
        Map<String, Object> mailMap = new HashMap<>();
        mailMap.put("receiverName", ""+userEntity.getFirstName()+" "+userEntity.getLastName());
        mailMap.put("to", userEntity.getEmail());
        mailMap.put("otp",otp);
        mailMap.put("subject", "OTP password EMIS");
        mailMap.put("templateName", "otp");
        exchanger.postRequest(userConfigs.getUrl(), mailMap);

        //Send via SMS
    }

    public boolean verifyOtp(OtpDTO otpDTO) {
        try {
            var otpEntity = dataService.findOTPByUserId(otpDTO.getUserId());
            log.info("OTP Entity Returned:{}", otpEntity);
            String encodedOtp = utilities.encoder(otpDTO.getOtp());
            log.info("Encoded OTP is:{}. Proceed to compare it with the OTP fetched from the DB", encodedOtp);
            if(encodedOtp.equals(otpEntity.getOtp())){
                log.info("OTP matches. Proceed to check if it is expired");
                  boolean isOtpExpired= isOtpExpired(otpEntity.getDateCreated());
                if(isOtpExpired){
                    log.info("The otp is expired");
                    return false;
                } else {
                    log.info("The otp is not expired proceed to invalidate and return true");
                    otpEntity.setStatus(userConfigs.getInvalidStatus());
                    dataService.saveOTP(otpEntity);
                    return true;
                }
            }
            return false;
        } catch (Exception e){
            log.error("Caught an exception:",e);
            return false;
        }
    }

    private boolean isOtpExpired(Date dateCreated){
        Date now = new Date();
        int duration = userConfigs.getOtpExpiryDurationInMinutes();
        log.info("About to check if date is expired given: Duration {} minutes. Date Created:{}. Current Date:{}",duration, dateCreated, now);
        // Create a Calendar instance and set it to the creation date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateCreated);

        // Add the expiry duration in minutes to the creation date
        calendar.add(Calendar.MINUTE, duration);

        // Get the expiry date from the calendar
        Date expiryDate = calendar.getTime();

        // Check if the current date is after the expiry date
        return now.after(expiryDate);
    }

    public ResponseDTO regenerateOtp(int userId){
        var userEntity = dataService.findByUserId(userId);
        var otpEntity = dataService.findOTPByUserId(userId);
        otpEntity.setStatus(userConfigs.getInvalidStatus());
        dataService.saveOTP(otpEntity);
        generateOTP(userEntity);
        return utilities.successResponse("Successfully regenerated otp",null);
    }

    public ResponseDTO changePassword(PasswordChangeDTO passwordChangeDTO) {
        UserEntity userEntity = dataService.findByEmail(passwordChangeDTO.getEmail()).get();
       userEntity.setPassword(passwordEncoder().encode(passwordChangeDTO.getPassword()));
       dataService.savePassword(userEntity);
        return utilities.successResponse("password changed successfully",null);

    }

    public ResponseDTO forgotPassword(ForgotPasswordDTO forgotPasswordDTO, int id) {
        {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userEntity = dataService.findByUserId(id);
            UserEntity user = modelMapper.map(forgotPasswordDTO, UserEntity.class);
            user.setPassword(passwordEncoder().encode(userEntity.getPassword()));
            log.info("send a request to reset password");
            dataService.savePassword(userEntity);
            return utilities.successResponse("password reset successfully", null);

        }
    }
}

