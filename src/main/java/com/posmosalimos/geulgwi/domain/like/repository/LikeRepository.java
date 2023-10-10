package com.posmosalimos.geulgwi.domain.like.repository;

import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

    @Query("select count(*) from Likes l where l.geulgwi.geulgwiSeq = :seq")
    int findLikeCountByGeulgwiSeq(Long seq);
}
