package com.posmosalimos.geulgwi.domain.geulgwi.repository;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.GeulgwiTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeulgwiTagRepository extends JpaRepository<GeulgwiTag, Long> {

    @Modifying
    @Query("delete from GeulgwiTag g where g.geulgwi = :geulgwi")
    void deleteByGeulgwi(@Param("geulgwi") Geulgwi geulgwi);
}
