package com.posmosalimos.geulgwi.api.user.friend.confirm.controller;

import com.posmosalimos.geulgwi.api.user.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.api.user.friend.confirm.service.FriendCnfmService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
@Slf4j
@RequiredArgsConstructor
public class FriendCnfmController {

    private final TokenManager tokenManager;
    private final FriendCnfmService friendCnfmService;

    @PostMapping("/confirm")
    public ResponseEntity<Boolean> confirm(@RequestPart("friendDTO") FriendDTO friendDTO,
                                           HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        friendCnfmService.confirm(friendDTO);
        log.info("friend - confirm({} -> {})", friendDTO.getFromUser(), friendDTO.getToUser());
        log.info("status: {}", )

        return ResponseEntity.ok(true);
    }
}
