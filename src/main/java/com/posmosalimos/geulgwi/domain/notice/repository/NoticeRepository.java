package com.posmosalimos.geulgwi.domain.notice.repository;

import com.posmosalimos.geulgwi.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
