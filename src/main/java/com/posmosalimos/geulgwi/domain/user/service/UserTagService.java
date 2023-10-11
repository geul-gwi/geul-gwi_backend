package com.posmosalimos.geulgwi.domain.user.service;

import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

}
