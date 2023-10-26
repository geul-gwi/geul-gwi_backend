package com.posmosalimos.geulgwi.api.friend.confirm.controller;

import com.posmosalimos.geulgwi.api.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.api.friend.confirm.service.FriendCnfmService;
import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
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
public class FriendCnfmController {

    private final TokenManager tokenManager;
    private final FriendCnfmService friendCnfmService;
    private final NoticeService noticeService;

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestBody FriendDTO friendDTO,
                                           HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        Friend friend = friendCnfmService.confirm(friendDTO);
        String status = noticeService.sendByFriend(friend); //친구 알림 저장 후 상태 리턴

        log.info("friend - confirm({} -> {})", friendDTO.getFromUser(), friendDTO.getToUser());
        log.info("notice - friend confirm({} -> {})", friendDTO.getFromUser(), friendDTO.getToUser());
        log.info("status - {}", status);

        return ResponseEntity.ok(status);
    }

}
