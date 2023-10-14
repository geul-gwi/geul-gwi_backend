package com.posmosalimos.geulgwi.domain.file.repository;

import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FileRepository extends JpaRepository<UploadFile, Long> {

    @Query("delete from UploadFile u where u.geulgwi = :geulgwi")
    void deleteByGeulgwi(@Param("geulgwi") Geulgwi geulgwi);
}
