//package com.posmosalimos.geulgwi.api.user.oauth.controller;
//
//import com.posmosalimos.geulgwi.api.user.oauth.client.KakaoTokenClient;
//import com.posmosalimos.geulgwi.api.user.oauth.dto.KakaoTokenDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class KakaoTokenController {
//
//    private final KakaoTokenClient kakaoTokenClient;
//
//    @Value("${kakao.client.id}")
//    private String clientId;
//
//    @Value("${kakao.client.secret")
//    private String clientSecret;
//
//    @Value("${kakao.redirect.url}")
//    private String redirectUri;
//
//    @GetMapping("/oauth/kakao/callback")
//    public @ResponseBody String loginCallback(String code) {
//
//        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
//
//        KakaoTokenDTO.Request kakaoTokenRequestDTO = KakaoTokenDTO.Request.builder()
//                .client_id(clientId)
//                .client_secret(clientSecret)
//                .grant_type("authorization_type")
//                .redirect_uri(redirectUri + "/oauth/kakao/callback")
//                .code(code)
//                .build();
//
//        KakaoTokenDTO.Response kakaoToken = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequestDTO);
//        return kakaoToken.toString();
//    }
//
//}
