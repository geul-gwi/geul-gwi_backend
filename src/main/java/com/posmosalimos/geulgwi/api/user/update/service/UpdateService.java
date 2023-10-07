package com.posmosalimos.geulgwi.api.user.update.service;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
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
    public void update(Long userSeq, UpdateDTO updateDTO, String storeFile) {

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
    }
}
