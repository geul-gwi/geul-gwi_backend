package com.posmosalimos.geulgwi.api.challenge.admin.search.service;

import com.posmosalimos.geulgwi.api.challenge.admin.register.dto.ChallengeFormDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeSrchAdminService {

    private final ChallengeAdminRepository challengeAdminRepository;

    public List<ChallengeFormDTO.Response> findAll() {

        List<ChallengeAdmin> challengeAdminList = challengeAdminRepository.findAll();
        List<ChallengeFormDTO.Response> challengeFormDTOS = new ArrayList<>();

        for (ChallengeAdmin challengeAdmin : challengeAdminList) {
            List<String> keywords = new ArrayList<>();
            keywords.add(challengeAdmin.getKeyword1());
            keywords.add(challengeAdmin.getKeyword2());
            keywords.add(challengeAdmin.getKeyword3());

            challengeFormDTOS.add(
                ChallengeFormDTO.Response.builder()
                        .challengeAdminSeq(challengeAdmin.getChallengeAdminSeq())
                        .comment(challengeAdmin.getComment())
                        .start(challengeAdmin.getStart())
                        .end(challengeAdmin.getEnd())
                        .keyword(keywords)
                        .build()
                );

        }

        return challengeFormDTOS;
    }

}
