package com.posmosalimos.geulgwi.api.notice.controller;

import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
@Slf4j
public class NoticeDelController {

    private final NoticeService noticeService;
    private final TokenManager tokenManager;

    @DeleteMapping("/delete/{noticeSeq}")
    public ResponseEntity<Boolean> delete(@PathVariable("noticeSeq") Long noticeSeq,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        noticeService.delete(noticeSeq);
        log.info("notice - delete(noticeSeq: {})", noticeSeq);

        return ResponseEntity.ok(true);
    }

}
