package com.posmosalimos.geulgwi.domain.user.repository;

import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select f from Friend f where f.toUser = :user and f.fromUser = :userSeq")
    Friend findByTwoUser(@Param("user") User user, @Param("userSeq") Long userSeq);

    @Query("delete from Friend f where f.toUser = :user and f.fromUser =:userSeq")
    void delete(@Param("user") User user, @Param("userSeq") Long userSeq);
}
