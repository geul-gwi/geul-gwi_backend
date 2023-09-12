package com.posmosalimos.geulgwi.api.challenge.update.service;

import com.posmosalimos.geulgwi.api.challenge.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ChallengeUdtService {

    private final ChallengeService challengeService;

    @Transactional
    public void update(Long userSeq,
                       Long challengeUserSeq,
                       @Valid ChallengeRegDTO challengeRegDTO) {

        ChallengeUser challengeUser = challengeService.findByChallengeUserSeq(challengeUserSeq);

        if (!userSeq.equals(challengeUser.getUser().getUserSeq()))
            throw new AuthenticationException(ErrorCode.NOT_EQUAL_MEMBER); //조건문 수정해야댐....시밠ㅂㅅㅂㅂㅅㅂㅁㅅㅇㅎㅁㅇㅎ

        challengeUser.update(challengeRegDTO.getChallengeContent());

        challengeService.update(challengeUser);
    }

}
