package com.posmosalimos.geulgwi.api.friend.search.service;

import com.posmosalimos.geulgwi.api.friend.search.dto.FriendListDTO;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendSrchService {

    private final FriendRepository friendRepository;

    public List<FriendListDTO> list(Long userSeq) {

        List<Friend> friendList = friendRepository.findByFromUser(userSeq);

        return friendList.stream().map(
                friend -> FriendListDTO.builder()
                        .userSeq(friend.getToUser().getUserSeq())
                        .userId(friend.getToUser().getUserId())
                        .nickname(friend.getToUser().getNickname())
                        .profile(friend.getToUser().getUploadFile().getStore())
                        .isSubscribed(friend.isSubscribe())
                        .build()).toList();
    }

    public List<Long> findSubscribers(User user) { //특정 회원을 구독한 회원들의 PK 리턴
        return friendRepository.findSubscriber(user).stream()
                .map(friend -> friend.getFromUser()).toList();
    }
}
