package com.posmosalimos.geulgwi.domain.user.repository;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByRefreshToken(String refreshToken);

    @Query("select u from User u left join fetch u.userTags where u.userSeq = :seq")
    Optional<User> findByUserSeq(@Param("seq") Long seq);

    Optional<User> findByUserId(String userId);

    @Query("select u from User u where u.nickname = :userNickname")
    Optional<User> findByUserNickname(@Param("userNickname") String nickname);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select u from User u where u.userId = :userId and u.email = :email")
    Optional<User> findByIdAndEmail(@Param("userId") String userId, @Param("email") String email);
}
