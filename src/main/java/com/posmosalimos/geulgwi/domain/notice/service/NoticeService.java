package com.posmosalimos.geulgwi.domain.notice.service;

import com.posmosalimos.geulgwi.domain.notice.entity.Notice;
import com.posmosalimos.geulgwi.domain.notice.repository.NoticeRepository;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public String sendByGeulgwi(List<Object> list) {
        Friend friend = (Friend) list.get(0);
        String status = (String) list.get(1);

        Notice notice = Notice.builder()
                .toUser(friend.getToUser())
                .fromUser(friend.getFromUser())
                .friendSeq(friend.getFriendSeq())
                .build();

        noticeRepository.save(notice);

        return status;
    }
}
