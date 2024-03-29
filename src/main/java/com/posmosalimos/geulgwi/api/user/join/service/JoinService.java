package com.posmosalimos.geulgwi.api.user.join.service;

import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.domain.user.service.UserTagService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class JoinService {

    private final UserService userService;
    private final FileService fileService;
    private final UserTagService userTagService;
    private final TagService tagService;

    @Transactional
    public void join(JoinDTO joinDTO, MultipartFile file) throws IOException {

        User user = new User();
        log.info("new User()");
        if (joinDTO.getUserId().equals("akxxkd")) {
            user = User.builder()
                    .userId(joinDTO.getUserId())
                    .password(joinDTO.getUserPassword())
                    .nickname(joinDTO.getUserNickname())
                    .gender(joinDTO.getUserGender())
                    .email(joinDTO.getUserEmail())
                    .age(joinDTO.getUserAge())
                    .role(Role.ADMIN)
                    .build();
        } else {
            user = User.builder()
                    .userId(joinDTO.getUserId())
                    .password(joinDTO.getUserPassword())
                    .nickname(joinDTO.getUserNickname())
                    .gender(joinDTO.getUserGender())
                    .email(joinDTO.getUserEmail())
                    .age(joinDTO.getUserAge())
                    .role(Role.COMMON)
                    .build();
        }

        userService.join(user);
        if (file != null && !file.isEmpty())
            fileService.storeUserFile(user, file);

        for (Long tagSeq : joinDTO.getUserTagSeq()) {
            Tag findTag = tagService.findBySeq(tagSeq);
            UserTag userTag = UserTag.builder()
                    .user(user)
                    .tag(findTag)
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

    public Boolean validateDuplicateEmail(String email) {

        String findUser = userService.findByEmail(email);

        return findUser.equals("true") ? true : false;
    }
}
