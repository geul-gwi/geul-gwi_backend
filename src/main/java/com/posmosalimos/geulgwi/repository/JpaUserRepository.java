package com.posmosalimos.geulgwi.repository;

import com.posmosalimos.geulgwi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<Users, Integer> {

    @Query("select u from Users u where u.userName = :userName")
    List<Users> findByUsername(@Param("userName") String userName);

    @Query("select u from Users u where u.userId = :userId")
    List<Users> findByUserId(@Param("userId") String userId);

}
