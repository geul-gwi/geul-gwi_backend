package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.form.Challenge.ChallengePostForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChallengeController {

    //챌린지 글 쓰기 페이지 매핑
    @GetMapping("/challenge/write")
    public String challengeMain(Model model) {
        model.addAttribute("challengePostForm", new ChallengePostForm());
        return "/challenge/writeForm";
    }

}
