package com.posmosalimos.geulgwi.api.challenge.admin.update.controller;

import com.posmosalimos.geulgwi.api.challenge.admin.register.dto.ChallengeFormDTO;
import com.posmosalimos.geulgwi.api.challenge.admin.update.dto.ChallengeUpdtDTO;
import com.posmosalimos.geulgwi.api.challenge.admin.update.service.ChallengeUpdtAdminService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/challenge/admin")
@RequiredArgsConstructor
public class ChallengeUpdtAdminController {

    private final TokenManager tokenManager;
    private final ChallengeUpdtAdminService challengeUpdtAdminService;

    @PostMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ChallengeUpdtDTO challengeUpdtDTO,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        challengeUpdtAdminService.update(challengeUpdtDTO);

        return ResponseEntity.ok(true);
    }


}
