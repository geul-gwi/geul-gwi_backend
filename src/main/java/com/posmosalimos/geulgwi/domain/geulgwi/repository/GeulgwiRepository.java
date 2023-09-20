package com.posmosalimos.geulgwi.domain.geulgwi.repository;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GeulgwiRepository extends JpaRepository<Geulgwi, Long> {

}
