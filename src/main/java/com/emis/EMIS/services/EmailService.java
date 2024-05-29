package com.emis.EMIS.services;

import com.emis.EMIS.configs.UserConfigs;
import com.emis.EMIS.models.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Setter
@Getter
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final UserConfigs userConfigs;

    public void send(UserEntity userEntity, String text){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userEntity.getEmail());
        msg.setSubject(userConfigs.getOtpSubjectMessage());
        msg.setText(text);
        javaMailSender.send(msg);
    }

}
