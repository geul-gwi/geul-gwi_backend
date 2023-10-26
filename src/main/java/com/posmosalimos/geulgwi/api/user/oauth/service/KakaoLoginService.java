package com.posmosalimos.geulgwi.api.user.oauth.service;

import com.posmosalimos.geulgwi.api.user.oauth.dto.KakaoDTO;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoLoginService {

    @Value("${kakao.client.id}")
    private String KAKAO_CLIENT_ID;

    @Value("${kakao.client.secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect.url}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }

    public KakaoDTO getKakaoInfo(String code) throws Exception {
        if (code == null) throw new Exception("Failed get authorization code");

        String accessToken = "";
        String refreshToken = "";

            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); //요청 헤더
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", "application/json");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            //요청 본문
            params.add("grant_type"   , "authorization_code");
            params.add("client_id"    , KAKAO_CLIENT_ID);
            params.add("redirect_uri" , KAKAO_REDIRECT_URL);
            params.add("code"         , code);
            params.add("client_secret", KAKAO_CLIENT_SECRET);

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers, params);

//            ResponseEntity<String> response = restTemplate.exchange(
//                    KAKAO_AUTH_URI + "/oauth/token", //url
//                    HttpMethod.POST, //method
//                    requestEntity, //request entity
//                    String.class //response type
//            ); //HTTP 요청 후 응답 값 저장

        ResponseEntity<String> response = restTemplate.postForEntity(KAKAO_AUTH_URI + "/oauth/token", requestEntity, String.class);

            JSONParser jsonParser = new JSONParser(response.getBody()); //응답 값을 jsonParser에 저장
            JSONObject jsonObj = (JSONObject) jsonParser.parse();

            accessToken  = (String) jsonObj.get("access_token");
            refreshToken = (String) jsonObj.get("refresh_token");

        return getUserInfoWithToken(accessToken);
    }

    private KakaoDTO getUserInfoWithToken(String accessToken) throws Exception {
        //HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        //Response 데이터 파싱
        JSONParser jsonParser = new JSONParser(response.getBody());
        JSONObject jsonObj = (JSONObject) jsonParser.parse();
        JSONObject account = (JSONObject) jsonObj.get("kakao_account");
        JSONObject profile = (JSONObject) account.get("profile");

        String account_email = String.valueOf(account.get("email"));
        String profile_nickname = String.valueOf(profile.get("nickname"));


        return KakaoDTO.builder()
                .email(account_email)
                .nickname(profile_nickname)
                .build();

    }

}