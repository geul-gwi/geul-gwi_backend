package com.posmosalimos.geulgwi.api.friend.subscribe.service;

import com.posmosalimos.geulgwi.api.friend.confirm.dto.FriendDTO;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.FriendRepository;
import com.posmosalimos.geulgwi.domain.user.repository.UserRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FriendSubsService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional
    public void toggleSubscription(FriendDTO friendDTO) {
        User fromUser = userRepository.findByUserSeq(friendDTO.getFromUser())
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN_FRIEND)); //요청한 사람
        User toUser = userRepository.findByUserSeq(friendDTO.getToUser())
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN_FRIEND)); //요청받을 사람

        Friend byToUser = friendRepository.findByTwoUser(toUser, fromUser.getUserSeq());
        Friend byFromUser = friendRepository.findByTwoUser(fromUser, toUser.getUserSeq());

        if (byToUser != null && byFromUser != null) //친구상태
            byToUser.toggleSubscription(); //요청한 사람 -> 요청받을 사람 구독/취소 처리
        else throw new BusinessException(ErrorCode.FORBIDDEN_FRIEND);
    }
}
