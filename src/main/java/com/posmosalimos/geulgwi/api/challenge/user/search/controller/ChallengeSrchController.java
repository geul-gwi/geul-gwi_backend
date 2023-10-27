package com.posmosalimos.geulgwi.api.challenge.user.search.controller;

import com.posmosalimos.geulgwi.api.challenge.user.search.dto.ChallengeAdminDTO;
import com.posmosalimos.geulgwi.api.challenge.user.search.dto.ChallengeSrchDTO;
import com.posmosalimos.geulgwi.api.challenge.user.search.service.ChallengeSrchService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge/user")
@RequiredArgsConstructor
@Slf4j
public class ChallengeSrchController {

    private final ChallengeSrchService challengeSearchService;
    private final TokenManager tokenManager;

    @GetMapping("/event/{challengeAdminSeq}") //개최 폼 조회(관리자 등록)
    public ResponseEntity<ChallengeAdminDTO> findChallengeAdmin(@PathVariable("challengeAdminSeq") Long challengeAdminSeq,
                                                                HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        ChallengeAdminDTO challenge = challengeSearchService.findByChallengeAdminSeq(challengeAdminSeq);
        log.info("challenge - get(challengeAdminSeq: {})", challengeAdminSeq);

        return ResponseEntity.ok(challenge);
    }

    @GetMapping("/list/{challengeAdminSeq}") //회차당 게시글 조회
    public ResponseEntity<List> findChallengeUser(@PathVariable("challengeAdminSeq") Long challengeAdminSeq,
                                                  @RequestParam("viewSeq") Long viewSeq,
                                                  HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<ChallengeSrchDTO> searchDTOS = challengeSearchService.findChallengeUsers(challengeAdminSeq, viewSeq);
        log.info("challenge - list(challengeAdminSeq: {})", challengeAdminSeq);

        return ResponseEntity.ok(searchDTOS);
    }

}
