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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    //글 쓰기 폼 매핑
    @GetMapping("/post/write")
    public String writePostForm(Model model) {
        model.addAttribute("writeForm", new WriteForm());
        return "/post/writeForm";
    }

    //글 쓰기
    @PostMapping("/post/write")
    public String write(BindingResult result, WriteForm form, HttpSession session) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "/post/writeForm";
        }

        Users loginUser = (Users) session.getAttribute("loginUser");
        postService.write(form, loginUser);

        return "redirect:/";
    }




    //글 수정 폼 매핑
    @GetMapping("/post/update/{seq}")
    public String UpdatePostForm(@PathVariable("seq")Long seq, Model model) {
        model.addAttribute("updateForm", new WriteForm());
        return "/post/updateForm";
    }

    //글 수정
    @PutMapping("/post/update/{seq}")
    public String update(@PathVariable("seq")Long seq, BindingResult result, @RequestBody WriteForm form) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "/post/updateForm";
        }

        postService.update(form, seq);

        return "redirect:/";
    }

}