package com.posmosalimos.geulgwi.domain.user.service;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.repository.UserTagRepository;
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
}
