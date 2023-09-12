package com.posmosalimos.geulgwi.domain.challenge.service;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeAdminRepository;
import com.posmosalimos.geulgwi.domain.challenge.repository.ChallengeUserRepository;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeUserRepository challengeUserRepository;
    private final ChallengeAdminRepository challengeAdminRepository;

    public void register(ChallengeUser challengeUser) {
        challengeUserRepository.save(challengeUser);
    }

    public List<ChallengeUser> findByAdminSeq(Long adminSeq){

        ChallengeAdmin findAdmin = challengeAdminRepository.findById(adminSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHALLENGE));

        return findAdmin.getChallengeUsers();
    }

    public void update(ChallengeUser challengeUser) {
        challengeUserRepository.update(challengeUser.getChallengeUserSeq(), challengeUser.getChallengeContent(), challengeUser.getRegDate());
    }

    public ChallengeUser findByChallengeUserSeq(Long challengeUserSeq) {
        ChallengeUser challengeUser = challengeUserRepository.findBySeq(challengeUserSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        return challengeUser;
    }
}
