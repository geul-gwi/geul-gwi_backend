package com.posmosalimos.geulgwi.controller;


import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.User.*;
import com.posmosalimos.geulgwi.form.User.email.EmailAuthForm;
import com.posmosalimos.geulgwi.form.User.email.EmailForm;
import com.posmosalimos.geulgwi.form.User.sms.PhoneNumberForm;
import com.posmosalimos.geulgwi.form.User.sms.PhoneVerifyForm;
import com.posmosalimos.geulgwi.service.EmailService;
import com.posmosalimos.geulgwi.service.MessageService;
import com.posmosalimos.geulgwi.service.UserService;
import com.posmosalimos.geulgwi.session.SessionConst;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageService messageService;
    private final EmailService emailService;

    //회원가입 폼 매핑
    @GetMapping("/users/join")
    public String joinUserForm(Model model) {
        model.addAttribute("JoinForm", new UserForm());
        return "users/joinForm";
    }

    //회원가입 처리
    @PostMapping("/users/join")
    public String join(@Valid @RequestBody UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.info("join error");
            return "fail";
        }
        userService.join(form);
        log.info("join success");
        return "success";
    }

    //로그인 폼 매핑
    @GetMapping("/users/login")
    public String loginUserForm(Model model) {
        model.addAttribute("LoginForm", new LoginForm());
        return "users/loginForm";
    }

    //로그인 처리
    @PostMapping("/users/login")
    public String login(@Valid @RequestBody LoginForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "fail";
        }

        Users loginUser = userService.login(form.getUserId(), form.getUserPassword());

        if (loginUser.equals(null)) {
            log.info("해당하는 정보가 없습니다.");
            result.reject("login fail", "일치하는 정보가 없습니다.");
            return "fail";
        }

        log.info("login success");
        session.setAttribute("loginUser", loginUser);
        return "success";
    }

    //회원 정보 수정 폼 매핑
    @GetMapping("/users/update")
    public String updateUserForm(Model model) {
        model.addAttribute("UpdateForm", new UpdateForm());
        return "users/updateForm";
    }

    //회원 정보 수정
    @PostMapping("/users/update")
    public String update(@Valid @RequestBody UpdateForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()){
            log.info("에러 발생");
            return "fail";
        }
        Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        userService.updateUser(loginUser, form);
        log.info("update user info");
        return "success";
    }

    //회원 탈퇴 폼 매핑
    @GetMapping("/users/withdrawal")
    public String withdrawalUserForm(Model model) {
        model.addAttribute("WithdrawalForm", new WithdrawalForm());
        return "users/withdrawalForm";
    }

    //회원 탈퇴
    @PostMapping("/users/withdrawal")
    public String withdrawal(@Valid @RequestBody WithdrawalForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "fail";
        }

        Users user = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        if (user.getUserPassword().equals(form.getUserPassword())) {
            userService.withdrawalUser(user.getUserId(), user.getUserPassword());
            log.info("withdrawal user");
            session.removeAttribute(SessionConst.LOGIN_USER);
            return "success";
        } else return "fail";
    }

    //로그아웃
    @PostMapping("/users/logout")
    public String logout(HttpSession session){
        session.removeAttribute(SessionConst.LOGIN_USER);

        return "success";
    }

    //비밀번호 찾기 폼 매핑
    @GetMapping("/users/findPassword")
    public String findPasswordUserForm(Model model) {
        model.addAttribute("FindPasswordForm", new FindPasswordForm());
        return "users/findPasswordForm";
    }

    //비밀번호 찾기
    @PostMapping("/users/findPassword")
    public String findPassword(@Valid @RequestBody FindPasswordForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("에러 발생");
            return "fail";
        }

        String password = userService.findPassword(form.getUserId(), form.getUserName());
        if (password.equals("")) {
            log.info("일치하는 회원이 없습니다.");
            return "success";
        } else {
            session.setAttribute("password", password);
            return "fail";
        }
    }

    //문자 인증
    @PostMapping("/users/join/sms")
    public void smsAuth(@Valid @RequestBody PhoneNumberForm form, BindingResult result, HttpSession session){
        if (result.hasErrors()){
            log.info("에러 발생");
        }
        session.setAttribute("verifyNumber", String.valueOf((int)(Math.random()*1000)));
        messageService.sendMessage(form.getPhoneNumber(), String.valueOf(session.getAttribute("verifyNumber")));
    }

    @PostMapping("/users/join/verify")
    public void smsVerify(@Valid @RequestBody PhoneVerifyForm form, BindingResult result, HttpSession session){
        if (result.hasErrors()){
            log.info("에러 발생");
        }

        String verifyNumber = String.valueOf(session.getAttribute("verifyNumber"));
        if (verifyNumber.equals(form.getVerify())) {
            log.info("인증 완료"); //추후 기능 추가
            session.removeAttribute("verifyNumber");
        }
        else log.info("인증번호 불일치"); //추후 기능 추가
    }

    @PostMapping("/users/join/email")
    public void sendEmail(@Valid @RequestBody EmailForm form, BindingResult result, HttpSession session) throws MessagingException {
        if (result.hasErrors())
            log.info("에러 발생");
        String authNum = emailService.sendEmail(form.getEmail()); //인증 메일 전송 후, 인증번호 리턴
        session.setAttribute("authNum", authNum); //인증번호 세션에 저장
    }

    @PostMapping("/users/join/email/auth")
    public void emailAuth(@RequestBody EmailAuthForm form, BindingResult result, HttpSession session){
        if (result.hasErrors())
            log.info("에러 발생");

        String enterNum = form.getAuthNum(); //사용자가 입력한 인증번호
        String authNum = String.valueOf(session.getAttribute("authNum"));
        if (enterNum.equals(authNum))
            log.info("인증 완료"); //작업 필요
        else log.info("인증번호 불일치"); //작업 필요
    }
}