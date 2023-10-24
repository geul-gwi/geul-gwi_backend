package com.posmosalimos.geulgwi.api.notice.service;

import com.posmosalimos.geulgwi.api.notice.dto.NoticeDTO;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.message.entity.Message;
import com.posmosalimos.geulgwi.domain.notice.entity.Notice;
import com.posmosalimos.geulgwi.domain.notice.repository.NoticeRepository;
import com.posmosalimos.geulgwi.domain.user.entity.Friend;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserService userService;

    @Transactional
    public String sendByFriend(Friend friend) { //친구 신청(승인) 알림 전송

        Notice notice = Notice.builder()
                .toUser(friend.getToUser())
                .fromUser(friend.getFromUser())
                .checked(false)
                .friendSeq(friend.getFriendSeq())
                .build();

        noticeRepository.save(notice);

        return friend.isApproved() ? "승인" : "대기";
    }

    @Transactional
    public void sendByMessage(Message message) { //쪽지 알림 전송

        Notice notice = Notice.builder()
                .toUser(message.getReceiver())
                .fromUser(message.getSender().getUserSeq())
                .checked(false)
                .messageSeq(message.getMessageSeq())
                .build();

        noticeRepository.save(notice);
    }

    @Transactional
    public void sendByGeulgwi(Long geulgwiSeq, Long fromUser, List<Long> subscribers) { //구독자들에게 알림 전송

        for (Long subscriber : subscribers) {
            User findUser = userService.findBySeq(subscriber);
            Notice notice = Notice.builder()
                    .toUser(findUser)
                    .fromUser(fromUser)
                    .checked(false)
                    .geulgwiSeq(geulgwiSeq)
                    .build();

            noticeRepository.save(notice);
        }
    }

    @Transactional
    public void sendByLikeGeulgwi(Likes likes) { //좋아요 누른 회원 -> 글 작성자(글귀)

        Notice notice = Notice.builder()
                .toUser(likes.getGeulgwi().getUser())
                .fromUser(likes.getUser().getUserSeq())
                .checked(false)
                .geulgwiSeq(likes.getGeulgwi().getGeulgwiSeq())
                .geulgwiLikeSeq(likes.getGeulgwi().getGeulgwiSeq())
                .build();

        noticeRepository.save(notice);
    }

    @Transactional
    public void sendByLikeChallenge(Likes likes) { //좋아요 누른 회원 -> 글 작성자(챌린지)

        Notice notice = Notice.builder()
                .toUser(likes.getChallengeUser().getUser())
                .fromUser(likes.getUser().getUserSeq())
                .checked(false)
                .challengeSeq(likes.getChallengeUser().getChallengeAdmin().getChallengeAdminSeq())
                .challengeLikeSeq(likes.getChallengeUser().getChallengeUserSeq())
                .build();

        noticeRepository.save(notice);
    }

    @Transactional
    public void update(Long noticeSeq) { //알림 체크 확인
        Notice notice = noticeRepository.findById(noticeSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOTIFICATION_NOT_FOUND));

        notice.toggleChecking();
    }

    @Transactional
    public void delete(Long noticeSeq) { //알림 삭제
        Notice notice = noticeRepository.findById(noticeSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOTIFICATION_NOT_FOUND));

        noticeRepository.delete(notice);
    }

    public List<NoticeDTO> findByToUser(Long userSeq) {
        User toUser = userService.findBySeq(userSeq); //알림을 받을 유저
        List<Notice> fromUsers = noticeRepository.findByToUser(toUser); //알림을 보낸 유저들
        List<NoticeDTO> noticeDTOS = new ArrayList<>();
        for (Notice fromUser : fromUsers) {
            User findUser = userService.findBySeq(fromUser.getFromUser());
            noticeDTOS.add(
                    NoticeDTO.builder()
                            .noticeSeq(fromUser.getNoticeSeq())
                            .fromUser(findUser.getUserSeq())
                            .nickname(findUser.getNickname())
                            .profile(findUser.getUploadFile().getStore())
                            .friendSeq(fromUser.getFriendSeq())
                            .messageSeq(fromUser.getMessageSeq())
                            .geulgwiSeq(fromUser.getGeulgwiSeq())
                            .geulgwiLikeSeq(fromUser.getGeulgwiLikeSeq())
                            .challengeLikeSeq(fromUser.getChallengeLikeSeq())
                            .regDate(fromUser.getRegDate())
                            .checked(fromUser.isChecked())
                            .build());
        }

        return noticeDTOS;
    }
}
