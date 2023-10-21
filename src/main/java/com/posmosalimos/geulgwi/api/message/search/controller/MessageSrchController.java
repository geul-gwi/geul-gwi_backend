package com.posmosalimos.geulgwi.api.message.search.controller;

import com.posmosalimos.geulgwi.api.message.search.service.MessageSrchService;
import com.posmosalimos.geulgwi.api.message.send.dto.MessageDTO;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@Slf4j
@RequiredArgsConstructor
public class MessageSrchController {

    private final TokenManager tokenManager;
    private final MessageSrchService messageSrchService;

    @PostMapping("/receiver/list/{userSeq}") //받은 쪽지 리스트
    public ResponseEntity<List> listByReceived(@PathVariable("userSeq") Long userSeq,
                                               HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<MessageDTO.Response> messageDTOS = messageSrchService.listByReceiver(userSeq);

        log.info("message - listByReceived");
        return ResponseEntity.ok(messageDTOS);
    }

    @PostMapping("/sender/list/{userSeq}") //보낸 쪽지 리스트
    public ResponseEntity<List> listBySended(@PathVariable("userSeq") Long userSeq,
                                               HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<MessageDTO.Response> messageDTOS = messageSrchService.listBySender(userSeq);

        log.info("message - listBySended");
        return ResponseEntity.ok(messageDTOS);
    }

    @PostMapping("/search/{messageSeq}")
    public ResponseEntity<MessageDTO.Response> search(@PathVariable("messageSeq") Long messageSeq,
                                                      HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        MessageDTO.Response message = messageSrchService.search(messageSeq);

        log.info("message - search({messageSeq}");
        return ResponseEntity.ok(message);
    }

}
