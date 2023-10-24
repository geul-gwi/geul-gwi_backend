package com.posmosalimos.geulgwi.domain.user.repository;

import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select f from Friend f where f.toUser = :user and f.fromUser = :userSeq")
    Friend findByTwoUser(@Param("user") User user, @Param("userSeq") Long userSeq);

    @Modifying
    @Query("delete from Friend f where f.toUser = :user and f.fromUser =:userSeq")
    void delete(@Param("user") User user, @Param("userSeq") Long userSeq);

    @Query("select f from Friend f where f.fromUser = :userSeq and f.approved = true")
    List<Friend> findByFromUser(@Param("userSeq") Long userSeq); //friend list
}
