package com.posmosalimos.geulgwi.controller;


import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.LoginForm;
import com.posmosalimos.geulgwi.form.UserForm;
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

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입 폼 매핑
    @GetMapping("/users/new")
    public String createForm(Model model) {
        model.addAttribute("JoinUserForm", new UserForm());
        return "users/createUserForm";
    }

    //회원가입
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

    //로그인 폼 매핑
    @GetMapping("/users/login")
    public String joinUserForm(Model model) {
        model.addAttribute("LoginForm", new LoginForm());
        return "users/loginUserForm";
    }

    //로그인
    @PostMapping("/users/login")
    public String loginForm(@Valid LoginForm form, BindingResult result, HttpSession session){
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "users/loginUserForm";
        }

        Users loginUser = userService.login(form.getUserId(), form.getUserPassword());

        if (loginUser.equals(null)) {
            log.info("해당하는 정보가 없습니다.");
            result.reject("login fail", "일치하는 정보가 없습니다.");
            return "/users/loginUserForm";
        }

        log.info("login success");
        session.setAttribute("loginUser", loginUser);
        return "redirect:/";

    }
}