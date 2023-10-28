package com.posmosalimos.geulgwi.api.challenge.admin.update.service;

import com.posmosalimos.geulgwi.api.challenge.admin.update.dto.ChallengeUpdtDTO;
import com.posmosalimos.geulgwi.domain.challenge.constant.Status;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeUpdtAdminService {

    private final ChallengeAdminRepository challengeAdminRepository;

    @Transactional
    public void update(ChallengeUpdtDTO challengeUpdtDTO) {

        ChallengeAdmin challengeAdmin = challengeAdminRepository.findById(challengeUpdtDTO.getChallengeAdminSeq())
                .orElseThrow(() -> new BusinessException(ErrorCode.CHALLENGE_NOT_FOUND));

        challengeAdmin.update(
                challengeUpdtDTO.getComment(),
                challengeUpdtDTO.getKeyword(),
                challengeUpdtDTO.getStart(),
                challengeUpdtDTO.getEnd(),
                Status.from(challengeUpdtDTO.getStart()));
    }
}
