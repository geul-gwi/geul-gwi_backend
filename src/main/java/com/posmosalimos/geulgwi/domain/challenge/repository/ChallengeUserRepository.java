package com.posmosalimos.geulgwi.domain.challenge.repository;


import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChallengeUserRepository extends JpaRepository<ChallengeUser, Long> {
    @Query("select c from ChallengeUser c where c.challengeUserSeq = :challengeUserSeq")
    Optional<ChallengeUser> findBySeq(@Param("challengeUserSeq") Long seq);

    @Modifying
    @Query("delete from ChallengeUser c where c.challengeUserSeq = :challengeUserSeq")
    void delete(@Param("challengeUserSeq") Long challengeUserSeq);

    @Modifying
    @Query("update ChallengeUser c set c.challengeContent = :challengeContent, c.regDate = :challengeRegDate where c.challengeUserSeq = :challengeUserSeq")
    void update(@Param("challengeUserSeq") Long challengeUserSeq, @Param("challengeContent") String challengeContent, @Param("challengeRegDate") String challengeRegDate);

}
