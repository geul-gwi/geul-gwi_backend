package com.posmosalimos.geulgwi.api.message.search.service;

import com.posmosalimos.geulgwi.api.message.send.dto.MessageDTO;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageSrchService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public List<MessageDTO.Response> listByReceiver(Long userSeq) { //받은 쪽지 리스트

        User user = userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        List<Message> messages = messageRepository.findAllByReceiver(user);
        List<MessageDTO.Response> messageDTOS = new ArrayList<>();

        for(Message message : messages) {
            if (message.getDeletedByReceiver().equals("F")) { //삭제하지 않았으면 보낼 때 추가해서 보내줌
                messageDTOS.add(
                        MessageDTO.Response.builder()
                                .messageSeq(message.getMessageSeq())
                                .title(message.getTitle())
                                .content(message.getContent())
                                .senderSeq(message.getSender().getUserSeq())
                                .senderNickname(message.getSender().getNickname())
                                .senderProfile(Optional.ofNullable(message.getSender().getUploadFile())
                                        .map(UploadFile::getStore)
                                        .orElse(null))
                                .receiverSeq(message.getReceiver().getUserSeq())
                                .receiverNickname(message.getReceiver().getNickname())
                                .build());
            }
        }
        return messageDTOS;
    }

    public List<MessageDTO.Response> listBySender(Long userSeq) { //보낸 쪽지 리스트

        User user = userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        List<Message> messages = messageRepository.findAllBySender(user);
        List<MessageDTO.Response> messageDTOS = new ArrayList<>();

        for (Message message : messages) {
            if (message.getDeletedBySender().equals("F")) { //삭제하지 않았으면 보낼 때 추가해서 보내줌
                messageDTOS.add(
                        MessageDTO.Response.builder()
                                .messageSeq(message.getMessageSeq())
                                .title(message.getTitle())
                                .content(message.getContent())
                                .senderSeq(message.getSender().getUserSeq())
                                .senderNickname(message.getSender().getNickname())
                                .senderProfile(Optional.ofNullable(message.getSender().getUploadFile())
                                        .map(UploadFile::getStore)
                                        .orElse(null))
                                        .receiverSeq(message.getReceiver().getUserSeq())
                                .receiverNickname(message.getReceiver().getNickname())
                                .build());
            }
        }

        return messageDTOS;
    }

}
