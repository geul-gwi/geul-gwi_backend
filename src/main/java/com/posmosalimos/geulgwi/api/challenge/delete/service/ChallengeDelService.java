package com.posmosalimos.geulgwi.api.challenge.delete.service;

import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeUserRepository;
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
    private final UserRepository userRepository;
    private final ChallengeUserRepository challengeUserRepository;


    @Transactional
    public void delete(Long seq) {
        challengeUserRepository.delete(seq);
    }
}
