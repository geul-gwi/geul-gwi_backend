package com.posmosalimos.geulgwi.api.friend.subscribe.search.service;

import com.posmosalimos.geulgwi.api.friend.search.dto.FriendListDTO;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
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
import java.util.Optional;

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
            friendListDTOS.add(
                    FriendListDTO.builder()
                            .userSeq(subscribe.getToUser().getUserSeq())
                            .userId(subscribe.getToUser().getUserId())
                            .nickname(subscribe.getToUser().getNickname())
                            .profile(Optional.ofNullable(subscribe.getToUser().getUploadFile())
                                    .map(UploadFile::getStore)
                                    .orElse(null))
                            .build()
            );
        }

        return friendListDTOS;
    }
}
