package com.posmosalimos.geulgwi.api.message.delete.service;

import com.posmosalimos.geulgwi.domain.message.entity.Message;
import com.posmosalimos.geulgwi.domain.message.repository.MessageRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageDelService {

    private final MessageRepository messageRepository;

    @Transactional //받은 쪽지 삭제
    public void deleteByReceiver(Long messageSeq) {

        Message message = messageRepository.findById(messageSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.MESSAGE_NOT_FOUND));

        message.deleteByReceiver();
        if (message.getDeletedBySender().equals("Y") && message.getDeletedByReceiver().equals("Y"))
            messageRepository.delete(message);
    }

    @Transactional //보낸 쪽지 삭제
    public void deleteBySender(Long messageSeq) {
        Message message = messageRepository.findById(messageSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.MESSAGE_NOT_FOUND));

        message.deleteBySender();
        if (message.getDeletedBySender().equals("Y") && message.getDeletedByReceiver().equals("Y"))
            messageRepository.delete(message);
    }
}
