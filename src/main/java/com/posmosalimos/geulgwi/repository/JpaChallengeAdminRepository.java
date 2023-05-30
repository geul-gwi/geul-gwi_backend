package com.posmosalimos.geulgwi.repository;

import com.posmosalimos.geulgwi.entity.ChallengeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaChallengeAdminRepository extends JpaRepository<ChallengeAdmin, Long> {
    @Query("select c.keyword1, c.keyword2, c.keyword3 from ChallengeAdmin c where c.challengeAdminSeq = :keyword_seq")
    String findKeyword_seq(@Param("keyword_seq") Long keyword_seq);
}
