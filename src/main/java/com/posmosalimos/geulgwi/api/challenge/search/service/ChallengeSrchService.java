package com.posmosalimos.geulgwi.api.challenge.search.service;

import com.posmosalimos.geulgwi.api.challenge.search.dto.ChallengeSrchDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeSrchService {

    private final ChallengeService challengeService;

    public List<ChallengeSrchDTO> searchChallenges(Long adminSeq){

        List<ChallengeUser> challengeUserByAdminSeq = challengeService.findByAdminSeq(adminSeq);
        List<ChallengeSrchDTO> searchDtos = new ArrayList<>();

        for (ChallengeUser challenge : challengeUserByAdminSeq) {

            searchDtos.add(
                ChallengeSrchDTO.builder()
                        .seq(challenge.getChallengeUserSeq())
                        .challengeContent(challenge.getChallengeContent())
                        .regDate(challenge.getRegDate())
//                        .likes(challenge.getLikes())
                        .build()
            );

        }

        return searchDtos;
    }
}
