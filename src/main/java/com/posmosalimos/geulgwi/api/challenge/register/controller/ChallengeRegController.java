package com.posmosalimos.geulgwi.api.challenge.register.controller;

import com.posmosalimos.geulgwi.api.challenge.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.api.challenge.register.service.ChallengeRegService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
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
public class ChallengeRegController {

    private final ChallengeRegService challengeRegService;
    private final TokenManager tokenManager;
    private final UserService userService;

    @PostMapping("/register/{userSeq}")
    public ResponseEntity<Boolean> register(@Valid @RequestBody ChallengeRegDTO challengeRegDTO,
                                        @PathVariable("userSeq") Long userSeq,
                                        HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        User findUser = userService.findBySeq(userSeq);

        challengeRegService.register(challengeRegDTO, findUser);
        log.info("challenge - register success");

        return ResponseEntity.ok(true);
    }
}
