package com.posmosalimos.geulgwi.api.user.friend.confirm.service;

import com.posmosalimos.geulgwi.api.user.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.FriendRepository;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendCnfmService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    @Transactional
    public void confirm(FriendDTO friendDTO) {

        User toUser = userService.findBySeq(friendDTO.getToUser()); //요청받은
        User fromUser = userService.findBySeq(friendDTO.getFromUser()); //요청한
        boolean approved;

        if (friendRepository.findByTwoUser(toUser, fromUser.getUserSeq()) == null) //상호 요청이 아닌 상태
            approved = false;
        else approved = true;

        Friend friend = Friend.builder()
                .toUser(toUser)
                .fromUser(fromUser.getUserSeq())
                .approved(approved)
                .build();

        friendRepository.save(friend);
    }

}
