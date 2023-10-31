package com.posmosalimos.geulgwi.api.friend.subscribe.search.service;

import com.posmosalimos.geulgwi.api.friend.search.dto.FriendListDTO;
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
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SubscribeListService {

    private final UserService userService;
    private final FriendRepository friendRepository;

    public List<FriendListDTO> findByUserSeq(Long userSeq) {

        List<Friend> subscribeList = friendRepository.findSubscribe(userSeq);
        List<FriendListDTO> friendListDTOS = new ArrayList<>();

        for (Friend subscribe : subscribeList) {
            User fromUser = userService.findBySeq(subscribe.getFromUser());
            friendListDTOS.add(
                    FriendListDTO.builder()
                            .userSeq(fromUser.getUserSeq())
                            .userId(fromUser.getUserId())
                            .nickname(fromUser.getNickname())
                            .profile(fromUser.getUploadFile().getStore())
                            .build()
            );
        }

        return friendListDTOS;
    }
}
