package com.posmosalimos.geulgwi.domain.user.controller;

import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfo;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDto;
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
    public ResponseEntity<UserInfoDto> getUserInfo(@UserInfo UserInfoDto userInfoDto) {
        Long seq = userInfoDto.getUserSeq();
        UserInfoDto userInfo = userService.findUserInfo(seq);
        return ResponseEntity.ok(userInfoDto);
    }
}
