package com.posmosalimos.geulgwi.domain.message.repository;

import com.posmosalimos.geulgwi.domain.message.entity.Message;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiver(User user);
    List<Message> findAllBySender(User user);


}
