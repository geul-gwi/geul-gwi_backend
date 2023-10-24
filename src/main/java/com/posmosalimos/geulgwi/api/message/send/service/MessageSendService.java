package com.posmosalimos.geulgwi.api.message.send.service;

import com.posmosalimos.geulgwi.api.message.send.dto.MessageDTO;
import com.posmosalimos.geulgwi.domain.message.entity.Message;
import com.posmosalimos.geulgwi.domain.message.repository.MessageRepository;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.UserRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MessageSendService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public Message send(MessageDTO messageDTO) {

        User sender = userRepository.findByUserSeq(messageDTO.getSenderSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        User receiver = userRepository.findByUserSeq(messageDTO.getReceiverSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Message message = Message.builder()
                .title(messageDTO.getTitle())
                .content(messageDTO.getContent())
                .sender(sender)
                .receiver(receiver)
                .deletedByReceiver("N")
                .deletedBySender("N")
                .build();

        Message findMessage = messageRepository.save(message);

        return messageRepository.findById(findMessage.getMessageSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.MESSAGE_NOT_FOUND));
    }
}
