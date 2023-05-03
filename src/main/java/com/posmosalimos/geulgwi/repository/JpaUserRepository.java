package com.posmosalimos.geulgwi.repository;

import com.posmosalimos.geulgwi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<Users, Integer> {

    @Query("select u from Users u where u.userName = :userName")
    List<Users> findByUsername(@Param("userName") String userName);

    @Query("select u from Users u where u.userId = :userId")
    Optional<Users> findByUserId(@Param("userId") String userId);

    @Query("select u.userPassword from Users u where u.userId = :userId and u.userName = :userName")
    String findPasswordByIdAndName(@Param("userId") String userId, @Param("userName") String userName);

}