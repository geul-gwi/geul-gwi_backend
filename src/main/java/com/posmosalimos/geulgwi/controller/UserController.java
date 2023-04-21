package com.posmosalimos.geulgwi.controller;


import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.UserForm;
import com.posmosalimos.geulgwi.repository.JpaUserRepository;
import com.posmosalimos.geulgwi.service.UserService;
import com.posmosalimos.geulgwi.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final JpaUserRepository jpaMemberRepository;
    private final UserService userService;

    //회원 가입 폼 매핑
    @GetMapping("/users/new")
    public String createForm(Model model) {
        model.addAttribute("UserForm", new UserForm());
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String createUserForm(@Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.info("join error");
            return "users/createUserForm";
        }
        userService.join(form);
        log.info("join success");
        return "redirect:/";
    }

    @PostMapping("/users/login")
    public String loginForm(@Valid UserForm form, BindingResult result, HttpSession session){
        Users findUser = userService.login(form.getUserId(), form.getUserPassword());
        if (findUser == null){
            log.info("없는 정보입니다.");
            return "/users/loginForm";
        }
        session.setAttribute("loginUser", SessionConst.LOGIN_USER);
        return "redirect:/";
    }
}
