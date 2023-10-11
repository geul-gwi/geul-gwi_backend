package com.posmosalimos.geulgwi.domain.tag.repository;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

//    @Query("delete from Tag t where t.value = :value")
//    void delete(@Param("value") String value);

    @Query("select t from Tag t where t.value = :value")
    Tag findByValue(@Param("value") String value);

    @Query("select t from Tag t where t.type = :type")
    List<Tag> findByType(@Param("type") String type);
}
