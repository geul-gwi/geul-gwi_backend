package com.posmosalimos.geulgwi.api.challenge.user.delete.controller;

import com.posmosalimos.geulgwi.api.challenge.user.delete.service.ChallengeDelService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/challenge")
public class ChallengeDelController {

    private final ChallengeDelService challengeDelService;
    private final TokenManager tokenManager;

    @DeleteMapping("/delete/{challengeUserSeq}")
    public ResponseEntity<Boolean> deleteChallenge(@PathVariable("challengeUserSeq") Long challengeUserSeq,
                                                   HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        challengeDelService.delete(challengeUserSeq);
        log.info("challenge - delete");

        return ResponseEntity.ok(true);
    }
}