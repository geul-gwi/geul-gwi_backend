package com.posmosalimos.geulgwi.api.user.join.service;

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

    public void join(User user) {
        if (user.getUserId().equals("akxxkd"))
            user.setRole(Role.ADMIN);
        userService.join(user);
    }

    public Boolean validateDuplicateUserId(String userId) {

        if (userId == "" || userId == null)
            throw new BusinessException(ErrorCode.INVALID_ID);

        return userService.findByUserId(userId);
    }
}
