package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.form.Post.WriteForm;
import com.posmosalimos.geulgwi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/post/write")
    public String writeUserForm(Model model) {
        model.addAttribute("writeForm", new WriteForm());
        return "/post/writeForm";
    }

    @PostMapping("/post/write")
    public String write(BindingResult result, MultipartFile file) {

    }
}
