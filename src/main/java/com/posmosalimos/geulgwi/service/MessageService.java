package com.posmosalimos.geulgwi.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Value("${coolsms.geulgwi.apikey}")
    private String apiKey;

    @Value("${coolsms.geulgwi.apisecret}")
    private String apiSecret;

    @Value("${coolsms.geulgwi.fromnumber}")
    private String fromNumber;

    public void sendMessage(String toNumber, String randomNumber){

        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
        Message sms = new Message();

        sms.setFrom(fromNumber); //계정 내 등록된 발신번호
        sms.setTo(toNumber); //수신번호
        sms.setText("[Geul-Gwi]\n인증번호 " + randomNumber + "를 입력하세요."); //전송 메시지

        try {
            messageService.send(sms);
        }catch (NurigoMessageNotReceivedException e){
            System.out.println(e.getFailedMessageList());
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
