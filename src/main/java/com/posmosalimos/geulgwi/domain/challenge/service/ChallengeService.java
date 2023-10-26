package com.posmosalimos.geulgwi.domain.challenge.service;

import com.posmosalimos.geulgwi.api.challenge.user.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeUserRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {

    private final ChallengeUserRepository challengeUserRepository;
    private final ChallengeAdminRepository challengeAdminRepository;

    @Transactional
    public void register(ChallengeUser challengeUser) {
        challengeUserRepository.save(challengeUser);
    }

    public ChallengeAdmin findChallenge(Long adminSeq) {
        return challengeAdminRepository.findById(adminSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.CHALLENGE_NOT_FOUND));
    }

    public List<ChallengeUser> findByAdminSeq(Long adminSeq) {
        return challengeAdminRepository.findById(adminSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.CHALLENGE_NOT_FOUND)).getChallengeUsers();
    }

    @Transactional
    public void delete(Long seq) {
        challengeUserRepository.delete(seq);
    }

    public void validateKeyword(ChallengeRegDTO challengeRegDTO, String[] keywords) {
        for (String str : keywords) {
            if (!(challengeRegDTO.getChallengeContent().contains(str))) {
                log.info(str + " 키워드가 들어가지 않았습니다.");
                throw new AuthenticationException(ErrorCode.KEYWORD_MISSING);
            }
        }
    }

    public ChallengeUser findByChallengeUserSeq(Long challengeUserSeq) {
        ChallengeUser challengeUser = challengeUserRepository.findBySeq(challengeUserSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return challengeUser;
    }

}
