//package com.posmosalimos.geulgwi.api.user.oauth.client;
//
//import com.posmosalimos.geulgwi.api.user.oauth.dto.KakaoTokenDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.openfeign.SpringQueryMap;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//@FeignClient(url = "https://kauth.kakao.com", name = "kakaoTokenClient")
//public interface KakaoTokenClient {
//
//    @PostMapping(value = "/oauth/token", consumes = "application/json")
//    KakaoTokenDTO.Response requestKakaoToken(@RequestHeader("Content-type") String contentType,
//                                             @SpringQueryMap KakaoTokenDTO.Request request);
//}
