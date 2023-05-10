package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Post.WriteForm;
import com.posmosalimos.geulgwi.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/post/write")
    public String writeUserForm(Model model) {
        model.addAttribute("writeForm", new WriteForm());
        return "/post/writeForm";
    }

    @PostMapping("/post/write")
    public String write(BindingResult result, WriteForm form, HttpSession session) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "/post/writeForm";
        }

        Users loginUser = (Users)session.getAttribute("loginUser");
        postService.write(form, loginUser);

        return "redirect:/";
    }
}

//    @PostMapping("/post/write")
//    public String write(BindingResult result, MultipartFile file) {
//
//    }

