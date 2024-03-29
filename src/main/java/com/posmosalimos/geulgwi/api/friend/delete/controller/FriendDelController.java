package com.posmosalimos.geulgwi.api.friend.delete.controller;

import com.posmosalimos.geulgwi.api.friend.delete.service.FriendDelService;
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
public class FriendDelController {

    private final TokenManager tokenManager;
    private final FriendDelService friendDelService;

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestParam("toUser") Long toUser,
                                          @RequestParam("fromUser") Long fromUser,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        friendDelService.delete(toUser, fromUser);
        log.info("friend - delete({}, {})", fromUser, toUser);

        return ResponseEntity.ok(true);
    }
}
