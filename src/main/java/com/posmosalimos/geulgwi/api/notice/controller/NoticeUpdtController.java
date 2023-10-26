package com.posmosalimos.geulgwi.api.notice.controller;

import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
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
@RequestMapping("/notice")
@Slf4j
@RequiredArgsConstructor
public class NoticeUpdtController {

    private final TokenManager tokenManager;
    private final NoticeService noticeService;

    @PostMapping("/update/{noticeSeq}")
    public ResponseEntity<Boolean> update(@PathVariable("noticeSeq") Long noticeSeq, //알림 체크 여부
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        noticeService.update(noticeSeq);
        log.info("notice - update(checked)");

        return ResponseEntity.ok(true);
    }
}
