package com.posmosalimos.geulgwi.api.notice.service;

import com.posmosalimos.geulgwi.api.notice.dto.NoticeDTO;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.message.entity.Message;
import com.posmosalimos.geulgwi.domain.notice.constant.Type;
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
import java.util.Optional;

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
                .type(Type.FRIEND)
                .toUser(friend.getToUser())
                .fromUser(friend.getFromUser())
                .checked("F")
                .friendSeq(friend.getFriendSeq())
                .build();

        noticeRepository.save(notice);

        return friend.getApproved().equals("T") ? "승인" : "대기";
    }

    @Transactional
    public void sendByMessage(Message message) { //쪽지 알림 전송

        Notice notice = Notice.builder()
                .type(Type.MESSAGE)
                .toUser(message.getReceiver())
                .fromUser(message.getSender())
                .checked("F")
                .messageSeq(message.getMessageSeq())
                .build();

        noticeRepository.save(notice);
    }

    @Transactional
    public void sendByGeulgwi(Long geulgwiSeq, User fromUser, List<Long> subscribers) { //구독자들에게 알림 전송(글귀)

        for (Long subscriber : subscribers) {
            User findUser = userService.findBySeq(subscriber);
            Notice notice = Notice.builder()
                    .type(Type.GEULGWI)
                    .toUser(findUser)
                    .fromUser(fromUser)
                    .checked("F")
                    .geulgwiSeq(geulgwiSeq)
                    .build();

            noticeRepository.save(notice);
        }
    }

    @Transactional
    public void sendByChallenge(Long challengeUserSeq, User fromUser, List<Long> subscribers) { //구독자들에게 알림 전송(챌린지)
        for (Long subscriber : subscribers) {
            User findUser = userService.findBySeq(subscriber);
            Notice notice = Notice.builder()
                    .type(Type.CHALLENGE)
                    .toUser(findUser)
                    .fromUser(fromUser)
                    .checked("F")
                    .challengeSeq(challengeUserSeq)
                    .build();

            noticeRepository.save(notice);
        }
    }

    @Transactional
    public void sendByLikeGeulgwi(Likes likes) { //좋아요 누른 회원 -> 글 작성자(글귀)

        Notice notice = Notice.builder()
                .type(Type.LIKE_GEULGWI)
                .toUser(likes.getGeulgwi().getUser())
                .fromUser(likes.getUser())
                .checked("F")
                .geulgwiSeq(likes.getGeulgwi().getGeulgwiSeq())
                .geulgwiLikeSeq(likes.getGeulgwi().getGeulgwiSeq())
                .build();

        noticeRepository.save(notice);
    }

    @Transactional
    public void sendByLikeChallenge(Likes likes) { //좋아요 누른 회원 -> 글 작성자(챌린지)

        Notice notice = Notice.builder()
                .type(Type.LIKE_CHALLENGE)
                .toUser(likes.getChallengeUser().getUser())
                .fromUser(likes.getUser())
                .checked("F")
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

    public List<NoticeDTO> findByToUser(Long userSeq) { //알림 목록
        User toUser = userService.findBySeq(userSeq); //알림을 받을 유저
        List<Notice> fromUsers = noticeRepository.findByToUser(toUser).stream()
                .filter(fromUser -> fromUser.getFromUser() != fromUser.getToUser()).toList(); //알림을 보낸 유저들

        List<NoticeDTO> noticeDTOS = new ArrayList<>();
        for (Notice fromUser : fromUsers) {
            noticeDTOS.add(
                    NoticeDTO.builder()
                            .type(fromUser.getType().toString())
                            .noticeSeq(fromUser.getNoticeSeq())
                            .fromUser(fromUser.getFromUser().getUserSeq())
                            .nickname(fromUser.getFromUser().getNickname())
                            .profile(Optional.ofNullable(fromUser.getFromUser().getUploadFile())
                                    .map(UploadFile::getStore)
                                    .orElse(null))
                            .friendSeq(fromUser.getFriendSeq())
                            .messageSeq(fromUser.getMessageSeq())
                            .geulgwiSeq(fromUser.getGeulgwiSeq())
                            .geulgwiLikeSeq(fromUser.getGeulgwiLikeSeq())
                            .challengeSeq(fromUser.getChallengeSeq())
                            .challengeLikeSeq(fromUser.getChallengeLikeSeq())
                            .regDate(fromUser.getRegDate())
                            .checked(fromUser.getChecked())
                            .build());
        }

        return noticeDTOS;
    }
}
