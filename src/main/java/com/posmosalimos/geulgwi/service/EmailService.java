package com.posmosalimos.geulgwi.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender sender;
    private final SpringTemplateEngine templateEngine;
    private String authNum;

    //랜덤 인증 코드 생성
    public void createCode(){
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i=0; i<8; i++){
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }

        authNum = key.toString();
    }

    //메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException {

        createCode(); //인증 코드 생성
        String setFrom = "zvzv9808@gmail.com";
        String toEmail = email; //인증 키를 받을 계정
        String title = "Geul-Gwi 회원가입 인증번호"; //제목

        MimeMessage message = sender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom);//보낼 이메일 설정
        message.setText(setContext(authNum), "utf-8", "html");

        return message;
    }

    //실제 메일 전송
    public String sendEmail(String toEmail) throws MessagingException{

        //메일 전송에 필요한 정보 설명
        MimeMessage emailForm = createEmailForm(toEmail);

        //실제 메일 전송
        sender.send(emailForm);

        return authNum;
    }

    //타임리프를 이용한 context 설정
    public String setContext(String code){

        Context context = new Context();
        context.setVariable("code", code);

        return templateEngine.process("mail", context); //mail.html
    }
}
