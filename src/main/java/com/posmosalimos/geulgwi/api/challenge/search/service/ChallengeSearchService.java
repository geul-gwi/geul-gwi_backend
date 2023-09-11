package com.posmosalimos.geulgwi.api.challenge.search.service;

import com.posmosalimos.geulgwi.api.challenge.search.dto.ChallengeSearchDto;
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
public class ChallengeSearchService {

    private final ChallengeService challengeService;

    public List<ChallengeSearchDto> searchChallenges(Long adminSeq){

        List<ChallengeUser> challengeUserByAdminSeq = challengeService.findChallengeUserByAdminSeq(adminSeq);
        List<ChallengeSearchDto> searchDtos = new ArrayList<>();

        for (ChallengeUser challenge : challengeUserByAdminSeq){

            searchDtos.add(
                ChallengeSearchDto.builder()
                        .seq(challenge.getChallengeUserSeq())
                        .challengeContent(challenge.getChallengeContent())
                        .regDate(challenge.getRegDate())
                        .like(challenge.getLike())
                        .build()
            );

        }

        return searchDtos;
    }
}
