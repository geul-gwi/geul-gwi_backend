package com.posmosalimos.geulgwi.global.jwt.service;

import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import com.posmosalimos.geulgwi.global.jwt.constant.GrantType;
import com.posmosalimos.geulgwi.global.jwt.constant.TokenType;
import com.posmosalimos.geulgwi.global.jwt.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    private final String accessTokenExpirationTime;
    private final String refreshTokenExpirationTime;
    private final String tokenSecret;

    public JwtTokenDto createJwtTokenDto(Long seq, Role role){
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(seq, role, accessTokenExpireTime);
        String refreshToken = createRefreshToken(seq, refreshTokenExpireTime);

        return JwtTokenDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();
    }

    private Date createRefreshTokenExpireTime() {
        // 현재시간 + 15 min
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createAccessTokenExpireTime() {
        // 현재시간 + 2 week
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public String createAccessToken(Long seq, Role role, Date expirationTime){
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())                // token의 제목
                .setIssuedAt(new Date(System.currentTimeMillis()))  // token이 생성된 시간 (현재 시간)
                .setExpiration(expirationTime)                      // 만료 시간
                .claim("seq", seq)                              // 회원 id
                .claim("role", role)                          // 사용자 역할
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("type", "JWT")
                .compact();

        return accessToken;
    }

    public String createRefreshToken(Long seq, Date expirationTime){
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                .claim("seq", seq)
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("type", "JWT")
                .compact();

        return refreshToken;
    }

    // 토큰 검증
    public void validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token); //token parsing
        } catch (ExpiredJwtException e){
            log.info("token 만료", e);
            throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e){
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

    public Claims getTokenClaims(String token){
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e){
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }

        return claims;
    }
}
