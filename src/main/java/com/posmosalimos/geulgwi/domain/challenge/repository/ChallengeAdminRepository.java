package com.posmosalimos.geulgwi.domain.challenge.repository;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ChallengeAdminRepository extends JpaRepository<ChallengeAdmin, Long> {

    @Query("select c.keyword1, c.keyword2, c.keyword3 from ChallengeAdmin c where c.challengeAdminSeq = :keyword_seq")
    String findKeyword_seq(@Param("keyword_seq") Long keyword_seq);
}
