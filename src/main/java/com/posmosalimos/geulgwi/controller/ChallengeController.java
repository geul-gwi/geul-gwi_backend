package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.entity.ChallengeUser;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Challenge.ChallengeWriteForm;
import com.posmosalimos.geulgwi.service.ChallengeService;
import com.posmosalimos.geulgwi.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChallengeController {

    private final ChallengeService challengeService;

    //챌린지 글 쓰기
    @PostMapping("/challenge/write")
    public ResponseEntity<String> write(@Valid @RequestBody ChallengeWriteForm form, BindingResult result, HttpSession session) {
        Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        String res = challengeService.write(form, loginUser);

        if (res.equals("upload success"))
            return ResponseEntity.ok("success");
        else //upload fail
            return ResponseEntity.ok("fail");
    }

    //챌린지 글 수정
    @PostMapping("/challenge/update/{seq}")
    public ResponseEntity<String> update(@RequestBody ChallengeWriteForm form, @PathVariable Long seq, BindingResult result) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return ResponseEntity.ok("fail");
        }
        challengeService.update(form, seq);
        return ResponseEntity.ok("success");
    }

    //챌린지 글 조회
    @GetMapping("/challenge/view/{seq}")
    public ResponseEntity<ChallengeUser> view(@PathVariable("seq") Long seq, HttpSession session) {
        Users user = (Users) session.getAttribute("loginUser");
        String userId = user.getUserId();
        ChallengeUser challenge = challengeService.findBySeq(seq);
        challenge.getChallengeAdmin().getKeyword1(); // 프록시 초기화
        session.setAttribute("loginUser", userId); // 추후 코드 수정
        return ResponseEntity.ok(challenge);
    }
}