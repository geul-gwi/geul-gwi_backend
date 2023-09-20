package com.posmosalimos.geulgwi.api.geulgwi.register.controller;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.api.geulgwi.register.service.GeulgwiRegService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/geulgwi")
public class GeulgwiRegController {

    private final GeulgwiRegService geulgwiRegService;
    private final TokenManager tokenManager;
    private final UserService userService;

    @PostMapping("/register/{userSeq}")
    public ResponseEntity<Boolean> register(@Valid GeulgwiRegDTO geulgwiRegDTO,
                                            @PathVariable("userSeq") Long seq,
                                            HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authentication");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        User findUser = userService.findBySeq(seq);

        geulgwiRegService.register(geulgwiRegDTO, findUser);

        return ResponseEntity.ok(true);
    }
}
