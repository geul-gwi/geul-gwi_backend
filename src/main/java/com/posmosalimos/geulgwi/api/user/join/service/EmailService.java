package com.posmosalimos.geulgwi.api.user.join.service;

import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;
    private static int number;

    public Boolean validNumber(String num) {
        //인증번호 일치확인

        if (number != Integer.parseInt(num))
            throw new AuthenticationException(ErrorCode.NOT_EQUAL_CODE);

        return true;
    }

    public static void createNumber() {
        //랜덤 인증번호 생성

        number = (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값)
    }

    public MimeMessage createMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "글귀 서비스" + "</h3>";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

        return message;
    }

    public int sendMail(String mail){
        MimeMessage message = createMail(mail);
        javaMailSender.send(message);

        return number;
    }
}
