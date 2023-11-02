package com.posmosalimos.geulgwi.api.challenge.user.register.service;

import com.posmosalimos.geulgwi.api.challenge.user.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.api.friend.search.service.FriendSrchService;
import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class ChallengeRegService {

    private final ChallengeAdminRepository challengeAdminRepository;
    private final ChallengeService challengeService;
    private final UserService userService;
    private final FriendSrchService friendSrchService;
    private final NoticeService noticeService;

    @Transactional
    public void register(ChallengeRegDTO challengeRegDTO) {

        User findUser = userService.findBySeq(challengeRegDTO.getUserSeq());


        ChallengeAdmin challengeAdmin = challengeAdminRepository.findById(challengeRegDTO.getChallengeAdminSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.CHALLENGE_NOT_FOUND));

        List<String> keywords = new ArrayList<>();
        keywords.add(challengeAdmin.getKeyword1());
        keywords.add(challengeAdmin.getKeyword2());
        keywords.add(challengeAdmin.getKeyword3());

        ChallengeUser challengeUser = ChallengeUser.builder()
                .challengeRegDTO(challengeRegDTO)
                .user(findUser)
                .challengeAdmin(challengeAdmin)
                .build();

        challengeService.validateKeyword(challengeRegDTO, keywords);
        challengeService.register(challengeUser);

        List<Long> subscribers = friendSrchService.findSubscribers(challengeUser.getUser()); //작성자의 구독자들 리스트화
        noticeService.sendByChallenge(challengeUser.getChallengeUserSeq(), challengeUser.getUser(), subscribers); //구독자들에게 알림 전송
    }
}
