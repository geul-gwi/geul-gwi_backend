package com.posmosalimos.geulgwi.global.interceptor;

import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AdminAuthorizationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String role = (String) tokenClaims.get("role");
        if (!Role.ADMIN.equals(role))
            throw new AuthenticationException(ErrorCode.FORBIDDEN_ADMIN);

        return true;
    }
}
