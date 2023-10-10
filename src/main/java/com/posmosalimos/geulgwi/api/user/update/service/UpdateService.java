package com.posmosalimos.geulgwi.api.user.update.service;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UpdateService {

    private final UserService userService;

    @Transactional
    public UserInfoDTO update(Long userSeq, UpdateDTO.Request updateDTO, String storeFile) {

        User findUser = userService.findBySeq(userSeq);

        findUser.update(
                updateDTO.getPassword(),
                updateDTO.getNickname(),
                updateDTO.getTags().get(0),
                updateDTO.getTags().get(1),
                updateDTO.getTags().get(2),
                storeFile,
                updateDTO.getComment()
        );

        return UserInfoDTO.builder()
                .userSeq(userSeq)
                .userId(findUser.getUserId())
                .userPassword(findUser.getPassword())
                .nickname(findUser.getNickname())
                .comment(findUser.getComment())
                .role(findUser.getRole())
                .tag1(findUser.getTag1())
                .tag2(findUser.getTag2())
                .tag3(findUser.getTag3())
                .profile(findUser.getUserProfile())
                .build();
    }
}
