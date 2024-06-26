package com.emis.EMIS.services;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.enums.Status;
import com.emis.EMIS.models.OTPEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.utils.RandomGenerator;
import com.emis.EMIS.utils.Utilities;
import com.emis.EMIS.wrappers.responseDTOs.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OTPService {
    private final DataService dataService;
    private final UserConfigs userConfigs;
    private final Utilities utilities;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;


    /**
     * A method to generate otp
     * @param userEntity is tied to the otpEntity
     */

    public void generateOTP(UserEntity userEntity){
        //generate a random number
        String otp = RandomGenerator.generateChars(userConfigs.getOtpLength());
        //insert to the db
        OTPEntity otpEntity =new OTPEntity();
        otpEntity.setOtp(utilities.encoder(otp));
        otpEntity.setUserEntity(userEntity);
        dataService.saveOTP(otpEntity);
        //send out the otp
        emailService.send(userEntity, "Your OTP is "+otp);
        this.sendOTP(otp,userEntity);
    }

    /**
     * A method to send otp via email
     * @param otp is the otp
     * @param userEntity where we get the email
     */
    private void sendOTP( String otp,UserEntity userEntity){
        log.info("OTP:{}", otp);
        //Send via Email
       emailService.send(userEntity, "Your OTP is ");
       //Send via SMS
    }


    /**
     * This is a method to verify the otp
     * @param userId used to get the user's email
     * @param otp otp
     * @return response dto
     */
    public boolean verifyOtp(int userId, String otp) {
        try {
            var otpEntity = dataService.findOTPByUserId(userId);
            log.info("OTP Entity Returned:{}", otpEntity);
            String encodedOtp = utilities.encoder(otp);
            log.info("Encoded OTP is:{}. Proceed to compare it with the OTP fetched from the DB", encodedOtp);
            if(encodedOtp.equals(otpEntity.getOtp())){
                log.info("OTP matches. Proceed to check if it is expired");
                  boolean isOtpExpired= isOtpExpired(otpEntity.getDateCreated());
                if(isOtpExpired){
                    log.info("The otp is expired");
                    return false;
                } else {
                    log.info("The otp is not expired proceed to invalidate and return true");
                    otpEntity.setStatus(Status.INVALID);
                    dataService.saveOTP(otpEntity);
                    return true;
                }
            }else{
                log.info("OTP does not match - {}", encodedOtp);
            }
            return false;
        } catch (Exception e){
            log.error("Caught an exception:",e);
            return false;
        }
    }

    /**
     * A method to check if the otp is expired
     * @param dateCreated date created
     * @return response dto
     */
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

    /**
     * A method to regenerate otp
     * @param userId used to find the user
     * @return response dto
     */
    public ResponseDTO regenerateOtp(int userId){
        var userEntity = dataService.findByUserId(userId);
        var otpEntity = dataService.findOTPByUserId(userId);
        otpEntity.setStatus(Status.INVALID);
        dataService.saveOTP(otpEntity);
        generateOTP(userEntity);
        return utilities.successResponse("Successfully regenerated otp",null);
    }

    /**
     * A forgot password method
     * @param email user's email
     * @return response dto
     */
    public ResponseDTO forgotPassword(String email) {
        {
            UserEntity userEntity = dataService.findByEmail(email).get();
            if(userEntity != null){
                userEntity.setStatus(Status.INACTIVE);
                userEntity.setPassword(null);
                dataService.saveUser(userEntity);
                generateOTP(userEntity);
                //generate otp and send
                return utilities.successResponse("password reset successfully", null);
            }
            return utilities.failedResponse(400, "User not exist", null);

        }
    }
}

