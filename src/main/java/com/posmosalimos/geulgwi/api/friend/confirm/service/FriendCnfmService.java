package com.posmosalimos.geulgwi.api.friend.confirm.service;

import com.posmosalimos.geulgwi.api.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.FriendRepository;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
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

    @Transactional
    public Friend confirm(FriendDTO friendDTO) {

        User toUser = userService.findBySeq(friendDTO.getToUser()); //요청받은 회원
        User fromUser = userService.findBySeq(friendDTO.getFromUser()); //요청한 회원
        boolean approved;
        Friend alreadyPending = friendRepository.findByTwoUser(fromUser, toUser.getUserSeq());

        if (alreadyPending != null) {
            //상대로부터 기요청이 왔던 상태
            alreadyPending.isApproved();
            approved = true;
        } else approved = false;

        Friend friend = Friend.builder()
                .toUser(toUser)
                .fromUser(fromUser.getUserSeq())
                .approved(approved)
                .subscribe(false)
                .build();

        friendRepository.save(friend);

        Friend findFriend = friendRepository.findByTwoUser(toUser, fromUser.getUserSeq());

        return findFriend;
    }

}
