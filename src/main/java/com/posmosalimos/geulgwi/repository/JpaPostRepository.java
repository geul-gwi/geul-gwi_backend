package com.posmosalimos.geulgwi.repository;

import com.posmosalimos.geulgwi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<Post, Long> {

}
