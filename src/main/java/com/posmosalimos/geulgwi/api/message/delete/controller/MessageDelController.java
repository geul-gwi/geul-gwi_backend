package com.posmosalimos.geulgwi.api.message.delete.controller;

import com.posmosalimos.geulgwi.api.message.delete.service.MessageDelService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageDelController {

    private final TokenManager tokenManager;
    private final MessageDelService messageDelService;

    @PostMapping("/receiver/delete/{messageSeq}") //받은 쪽지 삭제
    public ResponseEntity<Boolean> deleteByReceiver(@PathVariable("messageSeq") Long messageSeq,
                                                    HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        messageDelService.deleteByReceiver(messageSeq);
        log.info("message - deleteByReceiver");

        return ResponseEntity.ok(true);
    }

    @PostMapping("/sender/delete/{messageSeq}") //보낸 쪽지 삭제
    public ResponseEntity<Boolean> deleteBySender(@PathVariable("messageSeq") Long messageSeq,
                                                  HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        messageDelService.deleteBySender(messageSeq);
        log.info("message - deleteBySender");

        return ResponseEntity.ok(true);
    }
}
