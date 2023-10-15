package com.posmosalimos.geulgwi.api.user.login.controller;

import com.posmosalimos.geulgwi.api.user.login.dto.LoginDTO;
import com.posmosalimos.geulgwi.api.user.login.service.LoginService;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO.Response> login(@RequestBody LoginDTO.Request requestDTO) {

        LoginDTO.Response jwtTokenResponseDTO = loginService.login(requestDTO.getUserId(), requestDTO.getUserPassword());
        log.info("user - login success(userSeq: {})", jwtTokenResponseDTO.getUserSeq());

        return ResponseEntity.ok(jwtTokenResponseDTO);
    }
}
