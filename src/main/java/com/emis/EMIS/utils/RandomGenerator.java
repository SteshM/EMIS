package com.emis.EMIS.utils;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.models.OTPEntity;
import com.emis.EMIS.models.UserEntity;
import com.emis.EMIS.services.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;
@RequiredArgsConstructor

@Component
public class RandomGenerator {
    private UserConfigs userConfigs;
    private final Utilities utilities;
    private final DataService dataService;


    static Random random = new Random();
    static String choices = "0123456789";
    public static String generateChars(int length){
        StringBuilder chars= new StringBuilder();
        for(int i = 0; i< length; i++){
            chars.append(choices.charAt(random.nextInt(choices.length())));
        }
        return chars.toString();
    }


}
