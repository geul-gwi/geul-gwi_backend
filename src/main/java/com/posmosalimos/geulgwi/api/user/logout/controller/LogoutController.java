package com.posmosalimos.geulgwi.api.user.logout.controller;

import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import com.posmosalimos.geulgwi.api.user.logout.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LogoutController {
    private final LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String accessToken = authorization.split(" ")[1];
        logoutService.logout(accessToken);

        return ResponseEntity.ok("logout success");
    }
}
