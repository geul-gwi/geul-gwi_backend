package com.posmosalimos.geulgwi.domain.like.service;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.like.repository.LikeRepository;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final GeulgwiService geulgwiService;
    private final ChallengeService challengeService;

    @Transactional
    public void likeGeulgwi(Long geulgwiSeq, Long userSeq) {

        User findUser = userService.findBySeq(userSeq);
        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);

        Likes likes = Likes.builder()
                .user(findUser)
                .geulgwi(findGeulgwi)
                .build();

        likeRepository.save(likes);

    }

    @Transactional
    public void unlikeGeulgwi(Long geulgwiSeq, Long userSeq) {

    }

    @Transactional
    public void likeChallengeUser(Long challengeUserSeq, Long userSeq) {
        User findUser = userService.findBySeq(userSeq);
        ChallengeUser findChallengeUser = challengeService.findByChallengeUserSeq(challengeUserSeq);

        Likes likes = Likes.builder()
                .challengeUser(findChallengeUser)
                .user(findUser)
                .build();

        likeRepository.save(likes);
    }
}
