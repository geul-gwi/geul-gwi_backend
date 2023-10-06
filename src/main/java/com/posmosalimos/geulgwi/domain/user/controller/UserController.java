package com.posmosalimos.geulgwi.domain.user.controller;

import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfo;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final TokenManager tokenManager;
    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> getUserInfo(@UserInfo UserInfoDTO userInfoDto,
                                                   HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        Long seq = userInfoDto.getUserSeq();
        UserInfoDTO userInfo = userService.findUserInfo(seq);
        return ResponseEntity.ok(userInfo);
    }
}
