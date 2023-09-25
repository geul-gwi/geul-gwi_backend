package com.posmosalimos.geulgwi.api.challenge.like.controller;

import com.posmosalimos.geulgwi.api.challenge.like.service.ChallengeLikeService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/challenge")
public class ChallengeLikeController {

    private final TokenManager tokenManager;
    private final ChallengeLikeService challengeLikeService;

    @PostMapping("/likeChallenge/{challengeUserSeq}/{userSeq}")
    public ResponseEntity<Boolean> likeChallenge(@PathVariable("challengeUserSeq") Long challengeUserSeq,
                                                 @PathVariable("userSeq") Long userSeq,
                                                 HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

//        challengeLikeService.likeChallenge(challengeUserSeq, userSeq);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/unlikeChallenge/{challengeUserSeq}/{userSeq}")
    public ResponseEntity<Boolean> unlikeChallenge(@PathVariable("challengeUserSeq") Long challengeUserSeq,
                                                   @PathVariable("userSeq") Long userSeq,
                                                   HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);



        return ResponseEntity.ok(true);
    }
}
