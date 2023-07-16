package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.ChallengeAdmin;
import com.posmosalimos.geulgwi.entity.ChallengeUser;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Challenge.ChallengeWriteForm;
import com.posmosalimos.geulgwi.repository.JpaChallengeAdminRepository;
import com.posmosalimos.geulgwi.repository.JpaChallengeUserRepository;
import com.posmosalimos.geulgwi.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class ChallengeService {
    private final JpaChallengeAdminRepository jpaChallengeAdminRepository;
    private final JpaChallengeUserRepository jpaChallengeUserRepository;
    private final JpaUserRepository jpaUserRepository;

    //write
    @Transactional
    public String write(ChallengeWriteForm form, Users user) {
        String[] keywords = jpaChallengeAdminRepository.findKeyword_seq(form.getKeyword_seq()).split(",");

        ChallengeAdmin challengeAdmin = ChallengeAdmin.builder()
                .keyword(keywords)
                .keyword_seq(form.getKeyword_seq())
                .build();

        ChallengeUser challengeUser = ChallengeUser.builder()
                .form(form)
                .user(user)
                .challengeAdmin(challengeAdmin)
                .build();

        for (String str : keywords) {
            if (!(form.getChallengeContent().contains(str))) {
                log.info(str + " 키워드가 들어가지 않았습니다.");
                return "upload failed";
            }
        }

        jpaChallengeUserRepository.save(challengeUser);
        return "upload success";
    }

    //update
    @Transactional
    public void update(ChallengeWriteForm form, Long seq) {
        ChallengeUser findChallengePost = jpaChallengeUserRepository.findById(seq)
                .orElseThrow(() -> new NullPointerException("해당 게시물이 존재하지 않습니다."));

        findChallengePost.update(form);
    }

    //delete
    @Transactional
    public String delete(Long seq, Users users) {
        Optional<Users> user = jpaUserRepository.findByUserId(users.getUserId());

        if (user.isEmpty())
            throw new NullPointerException("delete fail");

        jpaChallengeUserRepository.delete(seq);
        return "success";
    }

    //findBySeq
    public ChallengeUser findBySeq(Long seq) {
        ChallengeUser challenge = jpaChallengeUserRepository.findBySeq(seq)
                .orElseThrow(() -> new NullPointerException("에러 발생"));

//        challenge.getChallengeAdmin(); // 프록시 초기화

        return challenge;
    }
}
