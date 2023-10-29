package com.posmosalimos.geulgwi.domain.challenge.repository;

import com.posmosalimos.geulgwi.domain.challenge.constant.Status;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChallengeAdminRepository extends JpaRepository<ChallengeAdmin, Long> {

    @Query("select c from ChallengeAdmin c where c.status = :status")
    Optional<ChallengeAdmin> findByStatus(@Param("status") Status status);

}
