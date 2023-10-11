package com.posmosalimos.geulgwi.api.user.update.service;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.domain.user.service.UserTagService;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UpdateService {

    private final UserService userService;

    @Transactional
    public void update(Long userSeq, UpdateDTO.Request updateDTO, String storeFile) {

        User findUser = userService.findBySeq(userSeq);
        List<UserTag> userTags = findUser.getUserTags();
        List<Tag> tags = updateDTO.getTags();

        findUser.update(
                updateDTO.getPassword(),
                updateDTO.getNickname(),
                storeFile,
                updateDTO.getComment()
        );

        for (int i = 0; i < updateDTO.getTags().size(); i++)
            userTags.get(i).update(findUser, tags.get(i));
    }
}
