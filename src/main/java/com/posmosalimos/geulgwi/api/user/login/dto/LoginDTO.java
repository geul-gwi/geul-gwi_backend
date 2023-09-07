package com.posmosalimos.geulgwi.api.user.login.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.global.jwt.dto.JwtTokenDto;
import lombok.*;

import java.util.Date;

public class LoginDTO {
    @Getter @Setter
    public static class Request {
        private String id;
        private String password;
    }

    @Getter @Setter @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String grantType;
        private String accessToken;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date accessTokenExpireTime;
        private String refreshToken;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date refreshTokenExpireTime;

        // of 메서드를 Service에 만들지 않고 숨김
        // Service의 가독성을 위해 사용
        public static Response of(JwtTokenDto jwtTokenDto){
            return Response.builder()
                    .grantType(jwtTokenDto.getGrantType())
                    .accessToken(jwtTokenDto.getAccessToken())
                    .accessTokenExpireTime(jwtTokenDto.getAccessTokenExpireTime())
                    .refreshToken(jwtTokenDto.getRefreshToken())
                    .refreshTokenExpireTime(jwtTokenDto.getRefreshTokenExpireTime())
                    .build();
        }
    }
}