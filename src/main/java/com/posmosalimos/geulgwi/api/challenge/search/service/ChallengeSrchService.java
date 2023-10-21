package com.posmosalimos.geulgwi.api.challenge.search.service;

import com.posmosalimos.geulgwi.api.challenge.search.dto.ChallengeSrchDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import com.posmosalimos.geulgwi.domain.like.service.LikeService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
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
    private final UserService userService;
    private final LikeService likeService;

    public List<ChallengeSrchDTO> searchChallenges(Long adminSeq, Long userSeq) {

        User findUser = userService.findBySeq(userSeq);

        List<ChallengeUser> challengeUserByAdminSeq = challengeService.findByAdminSeq(adminSeq);
        List<ChallengeSrchDTO> searchDTOS = new ArrayList<>();

        for (ChallengeUser challenge : challengeUserByAdminSeq) {
            Boolean isLiked = likeService.findByChallenge(challenge, findUser);
            searchDTOS.add(ChallengeSrchDTO.from(challenge, isLiked));

        }

        return searchDTOS;
    }
}
