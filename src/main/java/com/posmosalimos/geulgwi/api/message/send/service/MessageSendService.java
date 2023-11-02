package com.posmosalimos.geulgwi.api.message.send.service;

import com.posmosalimos.geulgwi.api.message.send.dto.MessageDTO;
import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
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



@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MessageSendService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final NoticeService noticeService;

    @Transactional
    public void send(MessageDTO messageDTO) {

        User sender = userRepository.findByUserSeq(messageDTO.getSenderSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        User receiver = userRepository.findByUserSeq(messageDTO.getReceiverSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Message save = Message.builder()
                .title(messageDTO.getTitle())
                .content(messageDTO.getContent())
                .sender(sender)
                .receiver(receiver)
                .deletedByReceiver("F")
                .deletedBySender("F")
                .build();


        Message message = messageRepository.save(save);
        noticeService.sendByMessage(message); //메시지 알림 전송
    }
}
