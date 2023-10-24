package com.posmosalimos.geulgwi.domain.file.repository;

import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface FileRepository extends JpaRepository<UploadFile, Long> {

    @Modifying
    @Query("delete from UploadFile u where u.geulgwi = :geulgwi")
    void deleteByGeulgwi(@Param("geulgwi") Geulgwi geulgwi);

    @Query("select u From UploadFile u where u.user = :user")
    UploadFile findByUser(@Param("user") User user);

    @Modifying
    @Query("delete from UploadFile u where u.user = :user")
    void deleteByUser(@Param("user") User user);
}
