package com.posmosalimos.geulgwi.api.friend.subscribe.search.controller;

import com.posmosalimos.geulgwi.api.friend.search.dto.FriendListDTO;
import com.posmosalimos.geulgwi.api.friend.subscribe.search.service.SubscribeListService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@Slf4j
public class SubscribeListController {

    private final TokenManager tokenManager;
    private final SubscribeListService subscribeListService;

    @GetMapping("/list/subscribe/{userSeq}")
    public ResponseEntity<List> list(@PathVariable("userSeq") Long userSeq,
                                            HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<FriendListDTO> subscribeList = subscribeListService.findByUserSeq(userSeq);
        log.info("friend - subscribe(userSeq: {})", userSeq);

        return ResponseEntity.ok(subscribeList);
    }
}
