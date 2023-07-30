package com.posmosalimos.geulgwi.api.user.join.service;

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

    public String join(User user) {
        userService.joinUser(user);
        return "join success";
    }

}
