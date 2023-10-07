package com.posmosalimos.geulgwi.domain.file.repository;

import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<UploadFile, Long> {

}
