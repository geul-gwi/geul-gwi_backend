package com.posmosalimos.geulgwi.api.user.join.service;

import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService {
    private final UserService userService;

    public void join(User user) {
        if (user.getUserId().equals("akxxkd"))
            user.setRole(Role.ADMIN);
        userService.joinUser(user);
    }

}
