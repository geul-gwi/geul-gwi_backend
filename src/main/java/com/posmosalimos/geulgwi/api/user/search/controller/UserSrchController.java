package com.posmosalimos.geulgwi.api.user.search.controller;

import com.posmosalimos.geulgwi.api.user.search.dto.UserListDTO;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserSrchController {

    private final UserService userService;
    private final TokenManager tokenManager;

    @GetMapping("/list")
    public ResponseEntity<List> list(HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");

        String accessToken = authorization.split(" ")[1];
        tokenManager.validateToken(accessToken);

        List<UserListDTO> listDtos = userService.findUserInfos();
        log.info("user - list");

        return ResponseEntity.ok(listDtos);
    }

    @GetMapping("/detail/{seq}")
    public ResponseEntity<UserInfoDTO> detail(@PathVariable("seq") Long seq,
                                              HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        UserInfoDTO userInfo = userService.findUserInfo(seq);

        log.info("user - detail(userSeq: {})", seq);

        return ResponseEntity.ok(userInfo);
    }

}
