package com.posmosalimos.geulgwi.api.challenge.user.update.service;

import com.posmosalimos.geulgwi.api.challenge.user.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ChallengeUpdtService {

    private final ChallengeService challengeService;
    private final ChallengeAdminRepository challengeAdminRepository;

    @Transactional
    public void update(Long keywordSeq,
                       Long userSeq,
                       Long challengeUserSeq,
                       ChallengeRegDTO challengeRegDTO) {

        String[] keywords = challengeAdminRepository.findKeywordSeq(keywordSeq).split(",");
        ChallengeUser challengeUser = challengeService.findByChallengeUserSeq(challengeUserSeq);

        challengeUser.update(challengeRegDTO.getChallengeContent());

        challengeService.validateKeyword(challengeRegDTO, keywords);
    }

}
