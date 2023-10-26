package com.posmosalimos.geulgwi.api.challenge.admin.register.service;

import com.posmosalimos.geulgwi.api.challenge.admin.register.dto.ChallengeFormDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ChallengeRegAdminService {

    private final ChallengeAdminRepository challengeAdminRepository;

    @Transactional
    public void register(ChallengeFormDTO challengeFormDTO) {
        ChallengeAdmin challengeAdmin = ChallengeAdmin.builder()
                .keyword(challengeFormDTO.getKeyword())
                .start(challengeFormDTO.getStart())
                .end(challengeFormDTO.getEnd())
                .comment(challengeFormDTO.getComment())
                .build();

        challengeAdminRepository.save(challengeAdmin);
    }
}
