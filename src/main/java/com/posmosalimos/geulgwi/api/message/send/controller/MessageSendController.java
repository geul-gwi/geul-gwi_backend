package com.posmosalimos.geulgwi.api.message.send.controller;

import com.posmosalimos.geulgwi.api.message.send.dto.MessageDTO;
import com.posmosalimos.geulgwi.api.message.send.service.MessageSendService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageSendController {

    private final TokenManager tokenManager;
    private final MessageSendService messageService;

    @PostMapping("/send")
    public ResponseEntity<Boolean> send(@RequestBody MessageDTO messageDTO,
                                        HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        messageService.send(messageDTO);

        log.info("message - send");

        return ResponseEntity.ok(true);
    }

}
