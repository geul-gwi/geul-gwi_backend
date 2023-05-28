package com.posmosalimos.geulgwi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaChallengeRepository extends JpaRepository {
    @Query("select c.keyword1, c.keyword2, c.keyword3 from ChallengeAdmin c where c.challengeAdminSeq = :keyword_seq")
    List<String> findKeyword_seq(@Param("keyword_seq") int keyword_seq);
}
