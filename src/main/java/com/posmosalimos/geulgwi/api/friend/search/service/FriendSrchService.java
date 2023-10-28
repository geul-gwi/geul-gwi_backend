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

import java.util.ArrayList;
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
        User findUser = userService.findBySeq(userSeq);
        List<FriendListDTO> listDTOS = new ArrayList<>();

        if (status.equals("friend")) { //친구
            List<Friend> friendList = friendRepository.getFriendList(findUser);

            for (Friend friend : friendList) {
                User friendUser = userService.findBySeq(friend.getFromUser());

                listDTOS.add(
                        FriendListDTO.builder()
                                .userSeq(friendUser.getUserSeq())
                                .userId(friendUser.getUserId())
                                .nickname(friendUser.getNickname())
                                .profile(Optional.ofNullable(friendUser.getUploadFile())
                                        .map(UploadFile::getStore)
                                        .orElse(null))
                                .build()
                );
            }

        } else { //status.equals("pending")
            List<Friend> pendingList = friendRepository.getPendingList(findUser);

            for (Friend pending : pendingList) {
                User pendingUser = userService.findBySeq(pending.getFromUser());

                listDTOS.add(
                    FriendListDTO.builder()
                            .userSeq(pendingUser.getUserSeq())
                            .userId(pendingUser.getUserId())
                            .nickname(pendingUser.getNickname())
                            .profile(Optional.ofNullable(pendingUser.getUploadFile())
                                    .map(UploadFile::getStore)
                                    .orElse(null))
                            .build()
                );
            }
        }
        return listDTOS;
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
