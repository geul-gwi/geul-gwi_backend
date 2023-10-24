package com.posmosalimos.geulgwi.api.friend.list.service;

import com.posmosalimos.geulgwi.api.user.search.dto.UserListDTO;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.FriendRepository;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendListService {

    private final FriendRepository friendRepository;

    public List<UserListDTO> list(Long userSeq) {

        List<Friend> friendList = friendRepository.findByFromUser(userSeq);

        return friendList.stream().map(
                friend -> UserListDTO.builder()
                        .userSeq(friend.getToUser().getUserSeq())
                        .userId(friend.getToUser().getUserId())
                        .nickname(friend.getToUser().getNickname())
                        .profile(friend.getToUser().getUploadFile().getStore())
                        .build()).toList();
    }
}
