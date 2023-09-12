package com.posmosalimos.geulgwi.api.user.login.service;

import com.posmosalimos.geulgwi.api.user.login.dto.LoginDTO;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.jwt.dto.JwtTokenDto;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final UserService userService;
    private final TokenManager tokenManager;

    public LoginDTO.Response login(String id, String password) {

        User findUser = userService.findByIdAndPassword(id, password);
        log.info("user : {}", findUser.getUserId());

        JwtTokenDto jwtTokenDto;

        jwtTokenDto = tokenManager.createJwtTokenDto(findUser.getUserSeq(), findUser.getRole());
        findUser.updateRefreshToken(jwtTokenDto);

        return LoginDTO.Response.of(jwtTokenDto);
    }
}
