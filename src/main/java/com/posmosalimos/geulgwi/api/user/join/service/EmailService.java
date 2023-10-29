package com.posmosalimos.geulgwi.api.user.join.service;

import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;


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

    public MimeMessage createMail(String email) {
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setFrom(new InternetAddress("geul-gwi@geul-gwi.com","글귀","UTF-8"));
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("글귀 서비스 인증번호입니다.");
            String body = "";
            body += "<h3>" + "글귀 서비스" + "</h3>";
            body += "<h3>" + "요청하신 인증번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return message;
    }

    public void sendMail(String mail) {
        MimeMessage message = createMail(mail);
        javaMailSender.send(message);
    }

    public MimeMessage findPassword(String nickname, String password, String email) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setFrom(new InternetAddress("geul-gwi@geul-gwi.com","글귀","UTF-8"));
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("요청하신 비밀번호입니다.");
            String body = "";
            body += "<h3>" + "글귀 서비스" + "</h3>";
            body += "<h3>" + nickname + "회원님. 요청하신 비밀번호입니다." + "</h3>";
            body += "<h1>" + password + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return message;
    }
}
