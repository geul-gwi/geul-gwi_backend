package com.posmosalimos.geulgwi.api.user.update.service;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateService {
    private final UserService userService;

    public void update(Long userSeq, UpdateDTO updateDTO) {

        User findUser = userService.findBySeq(userSeq);

        findUser.update(
                updateDTO.getPassword(),
                updateDTO.getName(),
                updateDTO.getNickname(),
                updateDTO.getTag1(),
                updateDTO.getTag2(),
                updateDTO.getTag3(),
                updateDTO.getProfile()
        );
    }
}
