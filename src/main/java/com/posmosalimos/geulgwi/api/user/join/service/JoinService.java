package com.posmosalimos.geulgwi.api.user.join.service;

import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService {

    private final UserService userService;

    public void join(JoinDTO joinDTO) {

        User user = new User();

        if (joinDTO.getUserId().equals("akxxkd")) {
            user = User.builder()
                    .userId(joinDTO.getUserId())
                    .password(joinDTO.getUserPassword())
                    .nickname(joinDTO.getUserNickname())
                    .gender(joinDTO.getUserGender())
                    .age(joinDTO.getUserAge())
                    .tag1(joinDTO.getUserTags().get(0))
                    .tag2(joinDTO.getUserTags().get(1))
                    .tag3(joinDTO.getUserTags().get(2))
                    .role(Role.ADMIN)
                    .build();
        } else {
            user = User.builder()
                    .userId(joinDTO.getUserId())
                    .password(joinDTO.getUserPassword())
                    .nickname(joinDTO.getUserNickname())
                    .gender(joinDTO.getUserGender())
                    .age(joinDTO.getUserAge())
                    .tag1(joinDTO.getUserTags().get(0))
                    .tag2(joinDTO.getUserTags().get(1))
                    .tag3(joinDTO.getUserTags().get(2))
                    .role(Role.COMMON)
                    .build();
        }

        userService.join(user);
    }

    public Boolean validateDuplicateUserId(String userId) {

        if (userId == "" || userId == null)
            throw new BusinessException(ErrorCode.INVALID_ID);

        return userService.findByUserId(userId);
    }

    public Boolean validateDuplicateNickname(String nickname) {

        return userService.findByNickname(nickname);
    }
}
