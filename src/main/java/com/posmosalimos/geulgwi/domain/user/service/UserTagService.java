package com.posmosalimos.geulgwi.domain.user.service;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.repository.UserTagRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class UserTagService {

    private final UserTagRepository userTagRepository;

    @Transactional
    public void save(UserTag userTag) {
        userTagRepository.save(userTag);
    }

    @Transactional
    public void update(UserTag userTag) {
        userTagRepository.update(userTag.getTag(), userTag.getUser(), userTag.getUserTagSeq());
    }

    public List<UserTag> findByUser(User user) {
        return userTagRepository.findByUser(user);
    }

    public UserTag findBySeq(Long seq) {
        return userTagRepository.findById(seq).orElseThrow(() -> new BusinessException(ErrorCode.TAG_NOT_FOUND));
    }

    @Transactional
    public void delete(UserTag userTag) {
        userTagRepository.delete(userTag);
    }
}
