package com.posmosalimos.geulgwi.api.challenge.search.controller;

import com.posmosalimos.geulgwi.api.challenge.search.dto.ChallengeAdminDTO;
import com.posmosalimos.geulgwi.api.challenge.search.dto.ChallengeSrchDTO;
import com.posmosalimos.geulgwi.api.challenge.search.service.ChallengeSrchService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
@Slf4j
public class ChallengeSrchController {

    private final ChallengeSrchService challengeSearchService;
    private final TokenManager tokenManager;

    @GetMapping("/{challengeSeq}")
    public ResponseEntity<ChallengeAdminDTO> findChallengeAdmin(@PathVariable("challengeSeq") Long challengeSeq,
                                                               HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        ChallengeAdminDTO challenge = challengeSearchService.findChallenge(challengeSeq);
        log.info("challenge - get(challengeSeq: {})", challengeSeq);

        return ResponseEntity.ok(challenge);
    }


    @GetMapping("/list/{challengeSeq}")
    public ResponseEntity<List> findChallengeUser(@PathVariable("challengeSeq") Long challengeSeq,
                                                  @RequestParam("userSeq") Long userSeq,
                                                  HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<ChallengeSrchDTO> searchDTOS = challengeSearchService.findChallengeUsers(challengeSeq, userSeq);
        log.info("challenge - list(challengeSeq: {})", challengeSeq);
        return ResponseEntity.ok(searchDTOS);
    }
}
