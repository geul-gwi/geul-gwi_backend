package com.posmosalimos.geulgwi.api.user.login.controller;

import com.posmosalimos.geulgwi.api.user.login.dto.LoginDTO;
import com.posmosalimos.geulgwi.api.user.login.service.LoginService;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO.Response> login(@RequestBody LoginDTO.Request requestDTO) {
        LoginDTO.Response jwtTokenResponseDTO = loginService.login(requestDTO.getId(), requestDTO.getPassword());

        return ResponseEntity.ok(jwtTokenResponseDTO);
    }
}
