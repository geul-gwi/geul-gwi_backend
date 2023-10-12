package com.posmosalimos.geulgwi.api.challenge.delete.controller;

import com.posmosalimos.geulgwi.api.challenge.delete.service.ChallengeDelService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
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
    private final UserService userService;
    private final TokenManager tokenManager;

    @DeleteMapping("/delete/user/{userSeq}")
    public ResponseEntity<Boolean> delete(@PathVariable("userSeq") Long seq, HttpServletRequest httpServletRequest) {
        //유저가 작성한 챌린지 글 전체 삭제 !!!!!!오류나!!!!!!
        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        User findUser = userService.findBySeq(seq);

        challengeDelService.deletePosts(findUser);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete/{challengeUserSeq}")
    public ResponseEntity<Boolean> deleteChallenge(@PathVariable("challengeUserSeq") Long challengeUserSeq,
                                                   HttpServletRequest httpServletRequest) {
        // 챌린지 단일 삭제
        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        challengeDelService.delete(challengeUserSeq);

        return ResponseEntity.ok(true);
    }
}