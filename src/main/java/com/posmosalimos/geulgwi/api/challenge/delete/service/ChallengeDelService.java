package com.posmosalimos.geulgwi.api.challenge.delete.service;

import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeUserRepository;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class ChallengeDelService {

    private final ChallengeUserRepository challengeUserRepository;

    @Transactional
    public void deletePosts(User user) {
        user.getChallengePostList().clear();
    }

    @Transactional
    public void delete(Long seq) {
        challengeUserRepository.delete(seq);
    }
}
