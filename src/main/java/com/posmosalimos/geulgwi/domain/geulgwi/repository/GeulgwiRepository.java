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
    @Query("update Geulgwi g set g.geulgwiContent = :geulgwiContent, g.regDate = :regDate, g.file1 = :file1, g.file2 = :file2, g.file3 = :file3 where g.geulgwiSeq = :geulgwiSeq")
    void update(@Param("geulgwiSeq") Long geulgwiSeq, @Param("geulgwiContent") String geulgwiContent,
                @Param("regDate") String regDate, @Param("file1") String file1,
                @Param("file1") String file2, @Param("file1") String file3);

}
