package com.posmosalimos.geulgwi.repository;

import com.posmosalimos.geulgwi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userName = :userName")
    List<User> findByUsername(@Param("userName") String userName);

}
