package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Challenge.ChallengePostForm;
import com.posmosalimos.geulgwi.service.ChallengeService;
import com.posmosalimos.geulgwi.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@Controller
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
                return "/challenge/writeForm";
            }
            Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);
            challengeService.write(form, loginUser); //맞는 키워드값에 끼워넣어서 서비스단 작업
            return "redirect:/";
    }
}