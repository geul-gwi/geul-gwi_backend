package com.posmosalimos.geulgwi.domain.tag.repository;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
