package com.posmosalimos.geulgwi.api.token.controller;

import com.posmosalimos.geulgwi.api.token.dto.AccessTokenResponseDTO;
import com.posmosalimos.geulgwi.api.token.service.TokenService;
import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/access/issue")
    public ResponseEntity<AccessTokenResponseDTO> createAccessToken(HttpServletRequest httpServletRequest){
        String authorization = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String refreshToken = authorization.split(" ")[1];

        AccessTokenResponseDTO accessTokenResponseDTO = tokenService.createAccessTokenByRefreshToken(refreshToken);

        return ResponseEntity.ok(accessTokenResponseDTO);
    }
}
