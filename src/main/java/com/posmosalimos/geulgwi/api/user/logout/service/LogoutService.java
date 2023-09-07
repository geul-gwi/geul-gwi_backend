package com.posmosalimos.geulgwi.api.user.logout.service;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import com.posmosalimos.geulgwi.global.jwt.constant.TokenType;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class LogoutService {

    private final UserService userService;
    private final TokenManager tokenManager;

    public void logout(String accessToken){

        // 1. 토큰 검증
        tokenManager.validateToken(accessToken);

        // 2. 토큰 타입 검증
        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String tokenType = tokenClaims.getSubject();
        if (!TokenType.isAccessToken(tokenType))
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);

        // 3. 토큰 만료 처리
        Long seq = Long.valueOf((Integer) tokenClaims.get("seq"));
        User findUser = userService.findBySeq(seq);
        findUser.expireRefreshToken(LocalDateTime.now()); // 토큰 만료 시간을 현재 시간으로 update
    }
}
