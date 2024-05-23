package com.emis.EMIS.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {
    static Random random = new Random();
    static String choices = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateChars(int length){
        String chars= "";
        for(int i = 0; i< length; i++){
            chars += choices.charAt(random.nextInt(choices.length()));
        }
        return chars;
    }
}
