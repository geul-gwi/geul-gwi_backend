package com.posmosalimos.geulgwi.api.friend.confirm.service;

import com.posmosalimos.geulgwi.api.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.FriendRepository;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendCnfmService {

    private final FriendRepository friendRepository;
    private final UserService userService;
    private final NoticeService noticeService;

    @Transactional
    public String confirm(FriendDTO friendDTO) {

        User toUser = userService.findBySeq(friendDTO.getToUser()); //요청받은 회원
        User fromUser = userService.findBySeq(friendDTO.getFromUser()); //요청한 회원
        String approved = "F";
        Friend alreadyPending = friendRepository.findByTwoUser(fromUser, toUser)
                .orElse(null);

        if (alreadyPending != null) {
            //상대로부터 기요청이 왔던 상태
            alreadyPending.toggleApproval();
            approved = "T";
        }


        Friend save = Friend.builder()
                .toUser(toUser)
                .fromUser(fromUser)
                .approved(approved)
                .subscriber("F")
                .build();

        friendRepository.save(save);


        Friend friend = friendRepository.findByTwoUser(toUser, fromUser)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN_FRIEND));

        return noticeService.sendByFriend(friend); //친구 알림 저장 후 상태 리턴
    }
}