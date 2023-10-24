package com.posmosalimos.geulgwi.api.friend.list.controller;

import com.posmosalimos.geulgwi.api.friend.list.service.FriendListService;
import com.posmosalimos.geulgwi.api.user.search.dto.UserListDTO;
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
public class FriendListController {

    private final FriendListService friendListService;
    private final TokenManager tokenManager;

    @PostMapping("/list/{userSeq}")
    public ResponseEntity<List> list(@PathVariable("userSeq") Long userSeq,
                                     HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];
        tokenManager.validateToken(accessToken);

        List<UserListDTO> friendList = friendListService.list(userSeq);
        return ResponseEntity.ok(friendList);
    }
}
