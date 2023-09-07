package com.posmosalimos.geulgwi.domain.challenge.repository;


import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChallengeUserRepository extends JpaRepository<ChallengeUser, Long> {
    @Query("select u from ChallengeUser u where u.challengeUserSeq = :challengeUserSeq")
    Optional<ChallengeUser> findBySeq(@Param("challengeUserSeq") Long seq);

    @Modifying
    @Query("delete from ChallengeUser u where u.challengeUserSeq = :challengeUserSeq")
    void delete(@Param("challengeUserSeq") Long challengeUserSeq);
}
