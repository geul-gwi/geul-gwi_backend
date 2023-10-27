package com.posmosalimos.geulgwi.api.friend.search.service;

import com.posmosalimos.geulgwi.api.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.api.friend.search.dto.FriendListDTO;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.FriendRepository;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendSrchService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    public List<FriendListDTO> list(String status, Long userSeq) {
        //pending: fromUser -> toUser(approved = false)
        //friend: fromUser -> toUser(approved = true)
        List<Friend> friendList = friendRepository.getFriendList(userSeq);

        if (status.equals("friend")) { //친구
            return friendList.stream().
                    filter(friend -> friend.getApproved().equals("T"))
                    .map(friend -> FriendListDTO.builder()
                                    .userSeq(friend.getToUser().getUserSeq())
                                    .userId(friend.getToUser().getUserId())
                                    .nickname(friend.getToUser().getNickname())
                                    .profile(Optional.ofNullable(friend.getToUser().getUploadFile())
                                            .map(UploadFile::getStore)
                                            .orElse(null))
                                    .isSubscribed(friend.getSubscriber())
                                    .build())
                    .toList();

        } else { //승인 대기(pending)
            return friendList.stream().
                    filter(friend -> friend.getApproved().equals("F")).map(
                            friend -> FriendListDTO.builder()
                                    .userSeq(friend.getToUser().getUserSeq())
                                    .userId(friend.getToUser().getUserId())
                                    .nickname(friend.getToUser().getNickname())
                                    .profile(Optional.ofNullable(friend.getToUser().getUploadFile())
                                            .map(UploadFile::getStore)
                                            .orElse(null))
                                    .build()).toList();
        }

    }

    public String getStatus(FriendDTO friendDTO) {
        String status = "";
        User toUser = userService.findBySeq(friendDTO.getToUser());
        Friend findFriend = friendRepository.findByTwoUser(toUser, friendDTO.getFromUser()).orElse(null);

        if (findFriend == null)
            status = "stranger";
        else if (findFriend.getApproved().equals("T"))
            status = "friend";
        else if (findFriend.getApproved().equals("F"))
            status = "pending";

        return status;
    }

    public List<Long> findSubscribers(User user) { //특정 회원을 구독한 회원들의 PK 리턴
        return friendRepository.findSubscriber(user).stream()
                .map(friend -> friend.getFromUser()).toList();
    }
}
