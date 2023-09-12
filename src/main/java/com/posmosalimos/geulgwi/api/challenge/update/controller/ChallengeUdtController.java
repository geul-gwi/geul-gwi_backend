package com.posmosalimos.geulgwi.api.challenge.update.controller;

import com.posmosalimos.geulgwi.api.challenge.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.api.challenge.update.service.ChallengeUdtService;
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
public class ChallengeUdtController {

    private final TokenManager tokenManager;
    private final ChallengeUdtService challengeUdtService;

    @PostMapping("/update/{userSeq}/{challengeUserSeq}")
    public ResponseEntity<Boolean> update(@Valid @RequestBody ChallengeRegDTO challengeRegDTO,
                                          @PathVariable("userSeq") Long userSeq,
                                          @PathVariable("challengeUserSeq") Long challengeUserSeq,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        challengeUdtService.update(userSeq, challengeUserSeq, challengeRegDTO);

        return ResponseEntity.ok(true);
    }
}
