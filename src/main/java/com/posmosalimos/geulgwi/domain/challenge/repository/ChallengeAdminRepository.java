package com.posmosalimos.geulgwi.domain.challenge.repository;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChallengeAdminRepository extends JpaRepository<ChallengeAdmin, Long> {

    @Query("select c.keyword1, c.keyword2, c.keyword3 from ChallengeAdmin c where c.challengeAdminSeq = :keywordSeq")
    String findKeywordSeq(@Param("keywordSeq") Long keywordSeq);
}
