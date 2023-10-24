package com.posmosalimos.geulgwi.api.friend.delete.service;

import com.posmosalimos.geulgwi.api.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.FriendRepository;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class FriendDelService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    @Transactional
    public void delete(FriendDTO friendDTO) {
        User toUser = userService.findBySeq(friendDTO.getToUser()); //요청받은 유저
        User fromUser = userService.findBySeq(friendDTO.getFromUser()); //요청한 유저

        if ( friendRepository.findByTwoUser(toUser, fromUser.getUserSeq()) != null &&
        friendRepository.findByTwoUser(fromUser, toUser.getUserSeq()) != null) {
            friendRepository.delete(toUser, fromUser.getUserSeq());
            friendRepository.delete(fromUser, toUser.getUserSeq());
        } else throw new BusinessException(ErrorCode.FORBIDDEN_FRIEND);
    }
}
