package com.posmosalimos.geulgwi.api.challenge.user.update.service;

import com.posmosalimos.geulgwi.api.challenge.user.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.challenge.constant.Status;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import com.posmosalimos.geulgwi.domain.challenge.service.ChallengeService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ChallengeUpdtService {

    private final ChallengeService challengeService;

    @Transactional
    public void update(Long challengeUserSeq, ChallengeRegDTO challengeRegDTO) {

        ChallengeUser challengeUser = challengeService.findByChallengeUserSeq(challengeUserSeq);

        challengeUser.update(challengeRegDTO.getChallengeContent());

        List<String> keywords = new ArrayList<>();
        keywords.add(challengeUser.getChallengeAdmin().getKeyword1());
        keywords.add(challengeUser.getChallengeAdmin().getKeyword2());
        keywords.add(challengeUser.getChallengeAdmin().getKeyword3());

        challengeService.validateKeyword(challengeRegDTO, keywords);
    }

    @Transactional
    public void updateStatus(Long challengeAdminSeq, String status) {

        ChallengeAdmin challengeAdmin = challengeService.findByChallengeAdminSeq(challengeAdminSeq);

        challengeAdmin.updateStatus(Status.from(status));
    }
}
