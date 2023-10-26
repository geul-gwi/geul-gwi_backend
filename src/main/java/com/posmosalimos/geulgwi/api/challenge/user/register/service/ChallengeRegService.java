package com.posmosalimos.geulgwi.api.challenge.user.register.service;

import com.posmosalimos.geulgwi.api.challenge.user.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class ChallengeRegService {

    private final ChallengeAdminRepository challengeAdminRepository;
    private final ChallengeService challengeService;

    @Transactional
    public void register(ChallengeRegDTO challengeRegDTO, User user) {
        String[] keywords = challengeAdminRepository.findKeywordSeq(challengeRegDTO.getKeywordSeq()).split(",");

        ChallengeAdmin challengeAdmin = ChallengeAdmin.builder()
                .keyword(keywords)
                .keyword_seq(challengeRegDTO.getKeywordSeq())
                .build();

        ChallengeUser challengeUser = ChallengeUser.builder()
                .challengeRegDTO(challengeRegDTO)
                .user(user)
                .challengeAdmin(challengeAdmin)
                .build();

        challengeService.validateKeyword(challengeRegDTO, keywords);
        challengeService.register(challengeUser);
    }
}
