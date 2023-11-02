package com.posmosalimos.geulgwi.api.challenge.user.update.controller;

import com.posmosalimos.geulgwi.api.challenge.user.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.api.challenge.user.update.service.ChallengeUpdtService;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.like.service.LikeService;
import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/challenge")
public class ChallengeUpdtController {

    private final TokenManager tokenManager;
    private final ChallengeUpdtService challengeUdtService;
    private final LikeService likeService;
    private final NoticeService noticeService;

    @PostMapping("/update/{challengeUserSeq}")
    public ResponseEntity<Boolean> update(@Valid @RequestBody ChallengeRegDTO challengeRegDTO,
                                          @PathVariable("challengeUserSeq") Long challengeUserSeq, //글 쓴 사람
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        challengeUdtService.update(challengeUserSeq, challengeRegDTO);
        log.info("challenge - update(challengeSeq: {})", challengeUserSeq);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/like/{challengeUserSeq}/{userSeq}")
    public ResponseEntity<Boolean> like(@PathVariable("challengeUserSeq") Long challengeUserSeq,
                                         @PathVariable("userSeq") Long userSeq,
                                         HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        likeService.likeChallengeUser(challengeUserSeq, userSeq);

        log.info("challenge - like(userSeq:{})", userSeq);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/unlike/{challengeUserSeq}/{userSeq}")
    public ResponseEntity<Boolean> unlike(@PathVariable("challengeUserSeq") Long challengeUserSeq,
                                          @PathVariable("userSeq") Long userSeq,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        likeService.unlikeChallengeUser(challengeUserSeq, userSeq);
        log.info("challenge - unlike(userSeq: {})", userSeq);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/status")
    public ResponseEntity<Boolean> updateStatus(@RequestParam("challengeAdminSeq") Long challengeAdminSeq,
                                                @RequestParam("status") String status,
                                                HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        challengeUdtService.updateStatus(challengeAdminSeq, status);
        log.info("friend - change status({})", status);

        return ResponseEntity.ok(true);
    }
}
