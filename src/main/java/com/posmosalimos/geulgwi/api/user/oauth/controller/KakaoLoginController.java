package com.posmosalimos.geulgwi.api.user.oauth.controller;

import com.posmosalimos.geulgwi.api.user.join.service.JoinService;
import com.posmosalimos.geulgwi.api.user.oauth.dto.KakaoDTO;
import com.posmosalimos.geulgwi.api.user.oauth.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;
    private final JoinService joinService;

    @GetMapping("/oauth/kakao/callback")
    public ResponseEntity<KakaoDTO> callback(String code) throws Exception {
        KakaoDTO kakaoDTO = kakaoLoginService.getKakaoInfo(code);
        joinService.join(kakaoDTO);

        return ResponseEntity.ok(kakaoDTO);
    }

//    @GetMapping("/oauth/kakao/callback")
//    public @ResponseBody String loginCallback(String code) {
//        System.out.println("code" + code);
//        return code;
//    }
}
