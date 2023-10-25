package com.posmosalimos.geulgwi.api.friend.search.controller;

import com.posmosalimos.geulgwi.api.friend.search.dto.FriendListDTO;
import com.posmosalimos.geulgwi.api.friend.search.service.FriendSrchService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
@Slf4j
@RequiredArgsConstructor
public class FriendSrchontroller {

    private final FriendSrchService friendListService;
    private final TokenManager tokenManager;

    @PostMapping("/list/{ststus}/{userSeq}")
    public ResponseEntity<List> list(@PathVariable("status") String status,
                                     @PathVariable("userSeq") Long userSeq,
                                     HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];
        tokenManager.validateToken(accessToken);

        List<FriendListDTO> friendList = friendListService.list(status, userSeq); //status: friend, pending
        return ResponseEntity.ok(friendList);
    }
}
