package com.posmosalimos.geulgwi.api.friend.subscribe.controller;

import com.posmosalimos.geulgwi.api.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.api.friend.subscribe.service.FriendSubsService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
@Slf4j
@RequiredArgsConstructor
public class FriendSubsController {

    private final TokenManager tokenManager;
    private final FriendSubsService friendSubsService;

    @PostMapping("/subscribe")
    public ResponseEntity<Boolean> toggleSubscription(@RequestBody FriendDTO friendDTO,
                                             HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        friendSubsService.toggleSubscription(friendDTO);

        log.info("friend - subscribe({} -> {})", friendDTO.getFromUser(), friendDTO.getToUser());

        return ResponseEntity.ok(true);
    }
}
