package com.posmosalimos.geulgwi.domain.notice.repository;

import com.posmosalimos.geulgwi.domain.notice.entity.Notice;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("select n from Notice n where n.toUser = :toUser")
    List<Notice> findByToUser(@Param("toUser") User toUser);
}
