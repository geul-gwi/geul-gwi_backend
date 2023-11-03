package com.posmosalimos.geulgwi.api.friend.delete.service;

import com.posmosalimos.geulgwi.api.friend.confirm.dto.FriendDTO;
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

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class FriendDelService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    @Transactional
    public void delete(Long toUser, Long fromUser) {
        User findToUser = userService.findBySeq(toUser); //요청 받은 유저
        User findFromUser = userService.findBySeq(fromUser); //요청한 유저

        Friend byToUser = friendRepository.findByTwoUser(findToUser, findFromUser)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN_FRIEND));
        Friend byFromUser = friendRepository.findByTwoUser(findFromUser, findToUser)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN_FRIEND));

        if (byToUser != null && byFromUser != null) { //친구 상태
            friendRepository.delete(findToUser, findFromUser);
            friendRepository.delete(findFromUser, findToUser);
        } else throw new BusinessException(ErrorCode.FORBIDDEN_FRIEND);

    }
}
