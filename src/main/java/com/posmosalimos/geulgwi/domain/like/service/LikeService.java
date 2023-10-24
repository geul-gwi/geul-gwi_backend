package com.posmosalimos.geulgwi.domain.like.service;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.like.repository.LikeRepository;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
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
    public Likes likeGeulgwi(Long geulgwiSeq, Long userSeq) {

        User findUser = userService.findBySeq(userSeq);
        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);

        Likes likes = Likes.builder()
                .user(findUser)
                .geulgwi(findGeulgwi)
                .build();

        return likeRepository.save(likes);

    }

    @Transactional
    public void unlikeGeulgwi(Long geulgwiSeq, Long userSeq) {
        Likes findGeulgwi = likeRepository.findGeulgwiByUserSeq(geulgwiSeq, userSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.GEULGWI_NOT_FOUND));
        likeRepository.delete(findGeulgwi);
    }

    @Transactional
    public Likes likeChallengeUser(Long challengeUserSeq, Long userSeq) {
        User findUser = userService.findBySeq(userSeq);
        ChallengeUser findChallengeUser = challengeService.findByChallengeUserSeq(challengeUserSeq);

        Likes likes = Likes.builder()
                .challengeUser(findChallengeUser)
                .user(findUser)
                .build();

        return likeRepository.save(likes);
    }

    @Transactional
    public void unlikeChallengeUser(Long challengeUserSeq, Long userSeq) {
        Likes findChallengeUser = likeRepository.findChallengeUserByUserSeq(challengeUserSeq, userSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.CHALLENGE_NOT_FOUND));

        likeRepository.delete(findChallengeUser);
    }

    public Boolean findByChallenge(ChallengeUser challengeUser, User user) {
        Likes likes = likeRepository.findByChallenge(challengeUser, user);

        return likes != null ? true : false;

    }

    public boolean findByGeulgwi(Geulgwi geulgwi, User user) {
        Likes likes = likeRepository.findByGeulgwi(geulgwi, user);

        return likes != null ? true : false;
    }
}
