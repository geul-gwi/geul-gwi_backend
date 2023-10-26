package com.posmosalimos.geulgwi.api.token.service;

import com.posmosalimos.geulgwi.api.token.dto.AccessTokenResponseDTO;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.jwt.constant.GrantType;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    private final UserService userService;
    private final TokenManager tokenManager;

    public AccessTokenResponseDTO createAccessTokenByRefreshToken(String refreshToken) {

        User findUser = userService.findUserByRefreshToken(refreshToken);

        Date accessTokenExpireTime = tokenManager.createAccessTokenExpireTime();
        String accessToken = tokenManager.createAccessToken(findUser.getUserSeq(), findUser.getRole(), accessTokenExpireTime);

        return AccessTokenResponseDTO.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .build();
    }
}
