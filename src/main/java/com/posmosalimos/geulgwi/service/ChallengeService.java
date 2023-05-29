package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.ChallengeUser;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Challenge.ChallengePostForm;
import com.posmosalimos.geulgwi.repository.JpaChallengeAdminRepository;
import com.posmosalimos.geulgwi.repository.JpaChallengeUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class ChallengeService {
    private final JpaChallengeAdminRepository jpaChallengeAdminRepository;
    private final JpaChallengeUserRepository jpaChallengeUserRepository;

    @Transactional
    public String write(ChallengePostForm form, Users user) {
        ChallengeUser challengeUser = ChallengeUser.builder()
                .form(form)
                .user(user)
                .build();

        System.out.println(challengeUser.toString());

        String[] keywords = jpaChallengeAdminRepository.findKeyword_seq(form.getKeyword_seq()).split(",");

        for (String str : keywords) {
            if (!(form.getChallengeContent().contains(str))) {
                log.info(str + " 키워드가 들어가지 않았습니다.");
                return "upload failed";
            }
        }

        jpaChallengeUserRepository.save(challengeUser);
        return "upload success";
    }


}
