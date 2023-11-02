package com.posmosalimos.geulgwi.api.user.update.service;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.domain.user.service.UserTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UpdateService {

    private final UserService userService;
    private final UserTagService userTagService;
    private final TagService tagService;

    @Transactional
    public User update(Long userSeq, UpdateDTO.Request updateDTO) {

        User findUser = userService.findBySeq(userSeq);
        List<UserTag> userTags = userTagService.findByUser(findUser); //수정 전 유저 태그들

        userTags.forEach(userTagService::delete); //기존 태그 삭제

        List<Tag> tags = updateDTO.getUserTagSeq().stream()
                .map(tagService::findBySeq)
                .collect(Collectors.toList()); //수정 요청한 태그들

        for (int i = 0; i < updateDTO.getUserTagSeq().size(); i++) { //요청 태그 저장
            userTagService.save(
                    UserTag.builder()
                            .tag(tags.get(i))
                            .user(findUser)
                            .build()
            );
        }

        findUser.update(
                    updateDTO.getPassword(),
                    updateDTO.getNickname(),
                    updateDTO.getComment()
        );

        return findUser;
    }
}
