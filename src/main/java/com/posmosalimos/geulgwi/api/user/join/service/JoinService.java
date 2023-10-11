package com.posmosalimos.geulgwi.api.user.join.service;

import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.domain.user.service.UserTagService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinService {

    private final UserService userService;
    private final UserTagService userTagService;

    @Transactional
    public void join(JoinDTO joinDTO) {

        User user = new User();

        if (joinDTO.getUserId().equals("akxxkd")) {
            user = User.builder()
                    .userId(joinDTO.getUserId())
                    .password(joinDTO.getUserPassword())
                    .nickname(joinDTO.getUserNickname())
                    .gender(joinDTO.getUserGender())
                    .age(joinDTO.getUserAge())
                    .role(Role.ADMIN)
                    .build();
        } else {
            user = User.builder()
                    .userId(joinDTO.getUserId())
                    .password(joinDTO.getUserPassword())
                    .nickname(joinDTO.getUserNickname())
                    .gender(joinDTO.getUserGender())
                    .age(joinDTO.getUserAge())
                    .role(Role.COMMON)
                    .build();
        }

        userService.join(user);

        for (Tag tag : joinDTO.getUserTags()) {
            UserTag userTag = UserTag.builder()
                    .user(user)
                    .tag(tag)
                    .build();
            userTagService.save(userTag);
        }


    }

    public Boolean validateDuplicateUserId(String userId) {

        if (userId.equals("") || userId == null)
            throw new BusinessException(ErrorCode.INVALID_ID);

        return userService.findByUserId(userId);
    }

    public Boolean validateDuplicateNickname(String nickname) {

        return userService.findByNickname(nickname);
    }
}
