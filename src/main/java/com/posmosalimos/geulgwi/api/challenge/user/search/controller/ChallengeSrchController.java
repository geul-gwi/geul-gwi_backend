package com.posmosalimos.geulgwi.api.challenge.user.search.controller;

import com.posmosalimos.geulgwi.api.challenge.admin.register.dto.ChallengeFormDTO;
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
@RequestMapping("/challenge")
@RequiredArgsConstructor
@Slf4j
public class ChallengeSrchController {

    private final ChallengeSrchService challengeSearchService;
    private final TokenManager tokenManager;


    @GetMapping("/list") //회차 목록 전체 조회
    public ResponseEntity<List> list(HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<ChallengeFormDTO.Response> challengeFormDTOS = challengeSearchService.findAllChallengeAdmin();
        log.info("challenge - list(admin)");

        return ResponseEntity.ok(challengeFormDTOS);
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

    @GetMapping("/ongoing") //진행중 챌린지 찾기
    public ResponseEntity<ChallengeFormDTO.Response> ongoing(HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        ChallengeFormDTO.Response challengeFormDTO = challengeSearchService.findOngoing();
        log.info("challenge - search ongoing(challengeAdminSeq: {})", challengeFormDTO.getChallengeAdminSeq());

        return ResponseEntity.ok(challengeFormDTO);
    }

}
