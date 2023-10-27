package com.posmosalimos.geulgwi.api.challenge.admin.search.controller;

import com.posmosalimos.geulgwi.api.challenge.admin.register.dto.ChallengeFormDTO;
import com.posmosalimos.geulgwi.api.challenge.admin.search.service.ChallengeSrchAdminService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challelnge/admin")
@Slf4j
@RequiredArgsConstructor
public class ChallengeSrchAdminController {

    private final TokenManager tokenManager;
    private final ChallengeSrchAdminService challengeSrchAdminService;

    @GetMapping("/list")
    public ResponseEntity<List> list(HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<ChallengeFormDTO.Response> challengeFormDTOS = challengeSrchAdminService.findAll();
        log.info("challenge - list(adin)");

        return ResponseEntity.ok(challengeFormDTOS);
    }
}
