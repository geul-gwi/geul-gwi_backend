package com.posmosalimos.geulgwi.domain.challenge.service;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeUserRepository challengeUserRepository;

    public void register(ChallengeUser challengeUser) {
        challengeUserRepository.save(challengeUser);
    }
}
