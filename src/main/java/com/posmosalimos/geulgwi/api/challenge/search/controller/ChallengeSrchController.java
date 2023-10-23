package com.posmosalimos.geulgwi.api.challenge.search.controller;

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

    @GetMapping("/list/{challengeSeq}")
    public ResponseEntity<List> findChallengeUser(@PathVariable("challengeSeq") Long challengeSeq,
                                                  @RequestParam("userSeq") Long userSeq,
                                                  HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];
        tokenManager.validateToken(accessToken);

        List<ChallengeSrchDTO> searchDtos = challengeSearchService.searchChallenges(challengeSeq, userSeq);
        log.info("challenge - list({})", challengeSeq);
        return ResponseEntity.ok(searchDtos);
    }
}
