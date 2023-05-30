package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Challenge.ChallengeWriteForm;
import com.posmosalimos.geulgwi.service.ChallengeService;
import com.posmosalimos.geulgwi.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChallengeController {

    private final ChallengeService challengeService;

    //챌린지 글 쓰기 폼 매핑
    @GetMapping("/challenge/write")
    public String writeChallengeForm(Model model) {
        model.addAttribute("challengePostForm", new ChallengeWriteForm());
        return "/challenge/writeForm";
    }

    //챌린지 글 쓰기
    @PostMapping("/challenge/write")
    public String write(@Valid @RequestBody ChallengeWriteForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "fail";
        }
        Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        String res = challengeService.write(form, loginUser);

        if (res.equals("upload success"))
            return "success";
        else //upload fail
            return "fail";
    }

    //챌린지 글 수정 폼 매핑
    @GetMapping("/challenge/update")
    public String UpdateChallengeForm(Long seq, Model model) { //seq??
        model.addAttribute("updateForm", new ChallengeWriteForm());
        return "/challenge/updateForm";
    }

    //챌린지 글 수정
    @PostMapping("/challenge/update/{seq}")
    public String update(BindingResult result, @PathVariable Long seq, @RequestBody ChallengeWriteForm form) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "fail";
        }

        challengeService.update(form, seq);
        return "success";
    }
}