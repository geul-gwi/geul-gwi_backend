package com.posmosalimos.geulgwi.global.interceptor;

import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import com.posmosalimos.geulgwi.global.jwt.constant.TokenType;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. Authorization Header 검증
        String authorization = request.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        // 2. 토큰 검증
        String accessToken = authorization.split(" ")[1];
        tokenManager.validateToken(accessToken);

        // 3. 토큰 타입
        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String tokenType = tokenClaims.getSubject();
        if (!TokenType.isAccessToken(tokenType))
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);

        return true;
    }
}
