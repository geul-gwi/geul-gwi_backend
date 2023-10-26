package com.posmosalimos.geulgwi.api.challenge.admin.register.controller;

import com.posmosalimos.geulgwi.api.challenge.admin.register.dto.ChallengeFormDTO;
import com.posmosalimos.geulgwi.api.challenge.admin.register.service.ChallengeRegAdminService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
@Slf4j
public class ChallengeRegAdminController {

    private final TokenManager tokenManager;
    private final ChallengeRegAdminService challengeRegAdminService;

    @PostMapping("/event/register")
    public ResponseEntity<Boolean> event(@RequestBody ChallengeFormDTO challengeFormDTO,
                                         HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        challengeRegAdminService.register(challengeFormDTO);
        log.info("challenge - admin form register");

        return ResponseEntity.ok(true);
    }
}
