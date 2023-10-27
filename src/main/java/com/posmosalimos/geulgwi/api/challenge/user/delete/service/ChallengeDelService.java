package com.posmosalimos.geulgwi.api.challenge.user.delete.service;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class ChallengeDelService {

    private final ChallengeService challengeService;

    @Transactional
    public void delete(Long challengeUserSeq) {

        ChallengeUser findChallengeUser = challengeService.findByChallengeUserSeq(challengeUserSeq);
        challengeService.delete(findChallengeUser);
    }
}
