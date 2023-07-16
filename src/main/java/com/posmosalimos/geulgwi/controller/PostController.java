package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Post.WriteForm;
import com.posmosalimos.geulgwi.service.PostService;
import com.posmosalimos.geulgwi.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    //글 쓰기
    @PostMapping("/post/write")
    public ResponseEntity<String> write(@Valid @RequestBody WriteForm form, HttpSession session) {
        Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        postService.write(form, loginUser);

        return ResponseEntity.ok("success");
    }

    //글 수정
    public ResponseEntity<String> update(@PathVariable("seq") Long seq, @RequestBody WriteForm form) {
        postService.update(form, seq);
        return ResponseEntity.ok("success");
    }

}