package com.posmosalimos.geulgwi.api.notice.controller;

import com.posmosalimos.geulgwi.api.notice.dto.NoticeDTO;
import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
@Slf4j
@RequiredArgsConstructor
public class NoticeSrchController {

    private final TokenManager tokenManager;
    private final NoticeService noticeService;

    @GetMapping("/list/{userSeq}")
    public ResponseEntity<List> list(@PathVariable("userSeq") Long userSeq,
                                     HttpServletRequest httpServletRequest) { //특정 회원의 알림 목록

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<NoticeDTO> noticeDTOS = noticeService.findByToUser(userSeq);
        log.info("notice - list(userSeq: {})", userSeq);

        return ResponseEntity.ok(noticeDTOS);
    }
}
