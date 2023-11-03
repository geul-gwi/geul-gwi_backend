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

    @Query("select f from Friend f where f.toUser = :toUser and f.fromUser = :fromUser")
    Optional<Friend> findByTwoUser(@Param("toUser") User toUser, @Param("fromUser") User fromUser);

    @Modifying
    @Query("delete from Friend f where f.toUser = :toUser and f.fromUser =:fromUser")
    void delete(@Param("toUser") User toUser, @Param("fromUser") User fromUser);

    @Query("select f from Friend f where f.toUser = :user and f.approved = 'T'")
    List<Friend> getFriendList(@Param("user") User user); //friend list

    @Query("select f from Friend f where f.toUser = :user and f.approved = 'F'")
    List<Friend> getPendingList(@Param("user") User user); //pending list

    @Query("select f from Friend f where f.toUser = :toUser and f.subscriber = 'T'")
    List<Friend> findSubscriber(@Param("toUser") User toUser); //나를 구독한 회원 리스트

    @Query("select f from Friend f where f.fromUser = :fromUser and f.subscriber = 'T'")
    List<Friend> findSubscribe(@Param("fromUser") User fromUser); //내가 구독한 회원 리스트
}
