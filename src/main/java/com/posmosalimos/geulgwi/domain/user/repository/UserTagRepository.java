package com.posmosalimos.geulgwi.domain.user.repository;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {

    @Modifying
    @Query("update UserTag u set u.tag = :tag, u.user = :user where u.userTagSeq = :userTagSeq")
    void update(@Param("tag") Tag tag, @Param("user") User user, @Param("userTagSeq") Long userTagSeq);

    @Query("select u from UserTag u where u.user = :user")
    List<UserTag> findByUser(@Param("user") User user);
}
