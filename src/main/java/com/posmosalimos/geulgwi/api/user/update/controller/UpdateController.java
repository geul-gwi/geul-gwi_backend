package com.posmosalimos.geulgwi.api.user.update.controller;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.api.user.update.service.UpdateService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfo;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UpdateController {

    private final UpdateService updateService;
    private final TokenManager tokenManager;

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateDTO updateDTO,
                                             @UserInfo UserInfoDTO userInfoDto,
                                             HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String accessToken = authorization.split(" ")[1];
        // 토큰 유효성 체크
        tokenManager.validateToken(accessToken);

        updateService.update(userInfoDto.getUserSeq(), updateDTO);

        return ResponseEntity.ok(true);
    }
}
