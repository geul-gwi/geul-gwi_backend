package com.posmosalimos.geulgwi.domain.like.repository;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

    @Query("select l from Likes l where l.user.userSeq = :userSeq and l.geulgwi.geulgwiSeq = :geulgwiSeq")
    Optional<Likes> findGeulgwiByUserSeq(@Param("geulgwiSeq") Long geulgwiSeq, @Param("userSeq") Long userSeq);

    @Query("select l from Likes l where l.user.userSeq = :userSeq and l.challengeUser.challengeUserSeq = :challengeUserSeq")
    Optional<Likes> findChallengeUserByUserSeq(@Param("challengeUserSeq") Long challengeUserSeq, @Param("userSeq") Long userSeq);

    @Query("select l from Likes l where l.challengeUser = :challengeUser")
    Likes findByChallenge(@Param("challengeUser") ChallengeUser challengeUser);

}
