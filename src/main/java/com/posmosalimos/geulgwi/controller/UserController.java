package com.posmosalimos.geulgwi.controller;


import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.LoginForm;
import com.posmosalimos.geulgwi.form.UpdateForm;
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
    @GetMapping("/users/join")
    public String joinForm(Model model) {
        model.addAttribute("JoinForm", new UserForm());
        return "users/joinForm";
    }

    //회원가입 처리
    @PostMapping("/users/join")
    public String join(@Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.info("join error");
            return "users/joinForm";
        }
        userService.join(form);
        log.info("join success");
        return "redirect:/";
    }

    //로그인 폼 매핑
    @GetMapping("/users/login")
    public String loginForm(Model model) {
        model.addAttribute("LoginForm", new LoginForm());
        return "users/loginForm";
    }

    //로그인 처리
    @PostMapping("/users/login")
    public String login(@Valid LoginForm form, BindingResult result, HttpSession session){
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "users/loginForm";
        }

        Users loginUser = userService.login(form.getUserId(), form.getUserPassword());

        if (loginUser.equals(null)) {
            log.info("해당하는 정보가 없습니다.");
            result.reject("login fail", "일치하는 정보가 없습니다.");
            return "loginForm";
        }

        log.info("login success");
        session.setAttribute("loginUser", loginUser);
        return "redirect:/";
    }

    //수정 폼 매핑
    @GetMapping("/users/update")
    public String updateUserForm(Model model) {
        model.addAttribute("UpdateForm", new UpdateForm());
        return "users/updateForm";
    }

    //회원정보 수정
    @PostMapping("users/update")
    public String update(@Valid UpdateForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()){
            log.info("에러 발생");
            return "users/updateForm";
        }
        Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        userService.updateUser(loginUser.getUserId(), loginUser.getUserPassword(), form);
        return "redirect:/";
    }
}