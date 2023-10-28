package com.posmosalimos.geulgwi.domain.user.repository;

import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select f from Friend f where f.toUser = :user and f.fromUser = :userSeq")
    Optional<Friend> findByTwoUser(@Param("user") User user, @Param("userSeq") Long userSeq);

    @Modifying
    @Query("delete from Friend f where f.toUser = :user and f.fromUser =:userSeq")
    void delete(@Param("user") User user, @Param("userSeq") Long userSeq);

    @Query("select f from Friend f where f.toUser = :user and f.approved = 'T'")
    List<Friend> getFriendList(@Param("user") User user); //friend list

    @Query("select f from Friend f where f.toUser = :user and f.approved = 'F'")
    List<Friend> getPendingList(@Param("user") User user); //pending list

    @Query("select f from Friend f where f.toUser = :toUser and f.subscriber = 'T'")
    List<Friend> findSubscriber(@Param("toUser") User toUser); //subscriber list
}
