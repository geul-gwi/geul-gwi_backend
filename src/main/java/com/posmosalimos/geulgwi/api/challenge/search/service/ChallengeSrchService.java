package com.posmosalimos.geulgwi.api.challenge.search.service;

import com.posmosalimos.geulgwi.api.challenge.search.dto.ChallengeSrchDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import com.posmosalimos.geulgwi.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeSrchService {

    private final ChallengeService challengeService;
    private final LikeService likeService;

    public List<ChallengeSrchDTO> searchChallenges(Long adminSeq) {

        List<ChallengeUser> challengeUserByAdminSeq = challengeService.findByAdminSeq(adminSeq);
        List<ChallengeSrchDTO> searchDTOS = new ArrayList<>();

        for (ChallengeUser challenge : challengeUserByAdminSeq) {
            Boolean isLiked = likeService.findByChallenge(challenge);
            searchDTOS.add(ChallengeSrchDTO.from(challenge, isLiked));

        }

        return searchDTOS;
    }
}
