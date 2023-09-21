package com.posmosalimos.geulgwi.domain.geulgwi.repository;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GeulgwiRepository extends JpaRepository<Geulgwi, Long> {

    @Query("select g from Geulgwi g where g.geulgwiSeq = :geulgwiSeq")
    Optional<Geulgwi> findBySeq(@Param("geulgwiSeq") Long seq);

    @Modifying
    @Query("delete from Geulgwi g where g.geulgwiSeq = :geulgwiSeq")
    void delete(@Param("geulgwiSeq") Long seq);

    @Modifying
    @Query("update Geulgwi g set g.geulgwiContent = :geulgwiContent, g.regDate = :regDate where g.geulgwiSeq = :geulgwiSeq")
    void update(@Param("geulgwiSeq") Long geulgwiSeq, @Param("geulgwiContent") String geulgwiContent, @Param("regDate") String regDate);
}
