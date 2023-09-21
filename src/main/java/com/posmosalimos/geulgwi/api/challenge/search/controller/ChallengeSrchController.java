package com.posmosalimos.geulgwi.api.challenge.search.controller;

import com.posmosalimos.geulgwi.api.challenge.search.dto.ChallengeSrchDTO;
import com.posmosalimos.geulgwi.api.challenge.search.service.ChallengeSrchService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("challenge")
@RequiredArgsConstructor
public class ChallengeSrchController {

    private final TokenManager tokenManager;
    private final ChallengeSrchService challengeSearchService;

    @GetMapping("/search/{challengeSeq}")
    public ResponseEntity<List> findChallengeUser(@PathVariable("challengeSeq") Long seq,
                                                  HttpServletRequest httpServletRequest){

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<ChallengeSrchDTO> searchDtos = challengeSearchService.searchChallenges(seq);

        return ResponseEntity.ok(searchDtos);
    }
}