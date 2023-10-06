package com.posmosalimos.geulgwi.api.user.logout.controller;

import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import com.posmosalimos.geulgwi.api.user.logout.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LogoutController {
    private final LogoutService logoutService;

    @DeleteMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String accessToken = authorization.split(" ")[1];
        logoutService.logout(accessToken);

        return ResponseEntity.ok(true);
    }
}
