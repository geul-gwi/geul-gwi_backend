package com.posmosalimos.geulgwi.domain.like.repository;

import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

    @Query("select l from Likes l where l.user.userSeq = :userSeq and l.geulgwi.geulgwiSeq = :geulgwiSeq")
    Likes findGeulgwiByUserSeq(@Param("geulgwiSeq") Long geulgwiSeq, @Param("userSeq") Long userSeq);

    @Query("select l from Likes l where l.user.userSeq = :userSeq and l.challengeUser.challengeUserSeq = :challengeuserSeq")
    Likes findChallengeUserByUserSeq(@Param("challengeUserSeq") Long challengeUserSeq, @Param("userSeq") Long userSeq);

}
