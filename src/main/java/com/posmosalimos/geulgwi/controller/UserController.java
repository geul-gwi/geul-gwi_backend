package com.posmosalimos.geulgwi.controller;


import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.User.*;
import com.posmosalimos.geulgwi.service.UserService;
import com.posmosalimos.geulgwi.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String login(@Valid LoginForm form, BindingResult result, HttpSession session) {
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

    //회원 정보 수정 폼 매핑
    @GetMapping("/users/update")
    public String updateUserForm(Model model) {
        model.addAttribute("UpdateForm", new UpdateForm());
        return "users/updateForm";
    }

    //회원 정보 수정
    @PostMapping("/users/update")
    public String update(@Valid UpdateForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()){
            log.info("에러 발생");
            return "users/updateForm";
        }
        Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        userService.updateUser(loginUser, form);
        log.info("update user info");
        return "redirect:/";
    }

    //회원 탈퇴 폼 매핑
    @GetMapping("/users/withdrawal")
    public String withdrawalUserForm(Model model) {
        model.addAttribute("WithdrawalForm", new WithdrawalForm());
        return "users/withdrawalForm";
    }

    //회원 탈퇴
    @PostMapping("/users/withdrawal")
    public String withdrawal(@Valid WithdrawalForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "users/withdrawalForm";
        }

        Users user = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        if (user.getUserPassword().equals(form.getUserPassword())) {
            userService.withdrawalUser(user.getUserId(), user.getUserPassword());
            log.info("withdrawal user");
            session.removeAttribute(SessionConst.LOGIN_USER);
            return "redirect:/";
        } else return "users/withdrawalForm";
    }

    //로그아웃
    @PostMapping("/users/logout")
    public String logout(HttpSession session){
        session.removeAttribute(SessionConst.LOGIN_USER);

        return "redirect:/";
    }

    //비밀번호 찾기 폼 매핑
    @GetMapping("/users/findPassword")
    public String findPasswordUserForm(Model model) {
        model.addAttribute("FindPasswordForm", new FindPasswordForm());
        return "users/findPasswordForm";
    }

    //비밀번호 찾기
    @PostMapping("/users/findPassword")
    public String findPassword(@Valid FindPasswordForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "users/findPasswordForm";
        }

        String password = userService.findPassword(form.getUserId(), form.getUserName());
        if (password.equals("")) {
            log.info("일치하는 회원이 없습니다.");
            return "redirect:/";
        } else {
            session.setAttribute("password", password);
<<<<<<< HEAD
            return "users/findPassword";
=======
            return "users/resultPassword";
>>>>>>> 7c24dac8262163deb4de362aa878656dbbe97901
        }
    }
}