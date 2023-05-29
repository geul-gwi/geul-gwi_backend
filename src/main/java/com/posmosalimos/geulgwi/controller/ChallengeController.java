package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Challenge.ChallengePostForm;
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

    //챌린지 글 쓰기 페이지 매핑
    @GetMapping("/challenge/write")
    public String writePostForm(Model model) {
        model.addAttribute("challengePostForm", new ChallengePostForm());
        return "/challenge/writeForm";
    }

    @PostMapping("/challenge/write")
    public String write(@Valid @RequestBody ChallengePostForm form, BindingResult result, HttpSession session) {
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
}