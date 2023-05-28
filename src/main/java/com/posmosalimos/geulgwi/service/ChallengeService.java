package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.ChallengeUser;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Challenge.ChallengePostForm;
import com.posmosalimos.geulgwi.repository.JpaChallengeRepository;
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
    private final JpaChallengeRepository jpaChallengeRepository;

    @Transactional
    public String write(ChallengePostForm form, Users user) {
        ChallengeUser challengeUser = ChallengeUser.builder()
                .form(form)
                .user(user)
                .build();

        List<String> keywords = jpaChallengeRepository.findKeyword_seq(form.getKeyword_seq());

        try {
            for (int i = 0; i < keywords.size(); ) {
                if (!(form.getChallengeContent().contains(keywords.get(i)))) {
                    log.info(keywords.get(i) + "키워드가 들어가지 않았습니다");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String err = String.valueOf(System.err);
            return err;
        }

        jpaChallengeRepository.save(challengeUser);
        return "";
    }


}
