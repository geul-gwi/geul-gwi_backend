package com.posmosalimos.geulgwi.api.tag.register.service;

import com.posmosalimos.geulgwi.api.tag.register.dto.RegTagDTO;
import com.posmosalimos.geulgwi.domain.tag.constant.TagType;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.repository.TagRepository;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RegTagService {

//    private final TagService tagService;
    private final TagRepository tagRepository;
    private final UserService userService;

    @Transactional
    public Tag create(RegTagDTO regTagDTO, Long userSeq) {
        User findUser = userService.findBySeq(userSeq);
        Tag tag = new Tag();

        if (tagRepository.findByValue(regTagDTO.getValue()) == null) { //중복이 아님(새로운 태그)
            if (findUser.getRole() == Role.ADMIN) {
                tag = Tag.builder()
                        .backColor(regTagDTO.getBackColor())
                        .fontColor(regTagDTO.getFontColor())
                        .value(regTagDTO.getValue())
                        .tagType(TagType.DEFAULT.name())
                        .build();
            } else {
                tag = Tag.builder()
                        .backColor(regTagDTO.getBackColor())
                        .fontColor(regTagDTO.getFontColor())
                        .value(regTagDTO.getValue())
                        .tagType(TagType.USER_ADDED.name())
                        .build();
            }
            return tagRepository.save(tag);
        } else //중복 태그
            return tagRepository.findByValue(regTagDTO.getValue());
    }
}
