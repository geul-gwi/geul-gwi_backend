package com.posmosalimos.geulgwi.api.user.login.service;

import com.posmosalimos.geulgwi.api.user.login.dto.LoginDTO;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
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
    private final FileService fileService;

    public LoginDTO.Response login(String seq, String password) {

        User findUser = userService.findByIdAndPassword(seq, password);
        String profile = fileService.findByUser(findUser);
        log.info("user : {}", findUser.getUserSeq());

        JwtTokenDto jwtTokenDto;

        jwtTokenDto = tokenManager.createJwtTokenDto(findUser.getUserSeq(), findUser.getRole());
        findUser.updateRefreshToken(jwtTokenDto);

        return LoginDTO.Response.of(jwtTokenDto, findUser.getNickname(), findUser.getRole().toString(), profile);
    }
}
