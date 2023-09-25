package com.posmosalimos.geulgwi.domain.challenge.service;

import com.posmosalimos.geulgwi.api.challenge.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeUserRepository;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<ChallengeUser> findByAdminSeq(Long adminSeq){

        ChallengeAdmin findAdmin = challengeAdminRepository.findById(adminSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHALLENGE));

        return findAdmin.getChallengeUsers();
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

    @Transactional
    public void update(ChallengeUser challengeUser) {
        challengeUserRepository.update(challengeUser.getChallengeUserSeq(),
                challengeUser.getChallengeContent(),
                challengeUser.getRegDate());
    }

    public ChallengeUser findByChallengeUserSeq(Long challengeUserSeq) {
        ChallengeUser challengeUser = challengeUserRepository.findBySeq(challengeUserSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        return challengeUser;
    }

//    @Transactional
//    public void likes(Long challengeUserSeq) {
//        challengeUserRepository.likes(challengeUserSeq);
//    }
}
