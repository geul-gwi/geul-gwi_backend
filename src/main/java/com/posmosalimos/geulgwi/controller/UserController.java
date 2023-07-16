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
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageService messageService;
    private final EmailService emailService;

    //회원가입 처리
    @PostMapping("/users/join")
    public ResponseEntity<String> join(@Valid @RequestBody UserForm form) {
        userService.join(form);
        log.info("join success");
        return ResponseEntity.ok("success");

    }

    //로그인 처리
    @PostMapping("/users/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginForm form, HttpSession session) {
        Users loginUser = userService.login(form.getUserId(), form.getUserPassword());

        if (loginUser.equals(null)) {
            log.info("해당하는 정보가 없습니다.");
            throw new EntityNotFoundException("not exist");
        }

        log.info("login success");
        session.setAttribute("loginUser", loginUser);
        return ResponseEntity.ok(loginUser.getUserNickname());
    }

    //회원 정보 수정
    @PostMapping("/users/update")
    public ResponseEntity<String> update(@Valid @RequestBody UpdateForm form, HttpSession session) {
        Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        userService.updateUser(loginUser, form);
        log.info("update user info");
        return ResponseEntity.ok("success");
    }

    //회원 탈퇴
    @PostMapping("/users/withdrawal")
    public ResponseEntity<String> withdrawal(@Valid @RequestBody WithdrawalForm form, HttpSession session) {
        Users user = (Users) session.getAttribute(SessionConst.LOGIN_USER);
        if (user.getUserPassword().equals(form.getUserPassword())) {
            userService.withdrawalUser(user.getUserId(), user.getUserPassword());
            log.info("withdrawal user");
            session.removeAttribute(SessionConst.LOGIN_USER);
            return ResponseEntity.ok("success");
        } else
            throw new IllegalStateException("fail withdrawal");
    }

    //로그아웃
    @PostMapping("/users/logout")
    public String logout(HttpSession session){
        session.removeAttribute(SessionConst.LOGIN_USER);

        return "success";
    }

    //비밀번호 찾기
    @PostMapping("/users/findPassword")
    public ResponseEntity<String> findPassword(@Valid @RequestBody FindPasswordForm form) {
        String password = userService.findPassword(form.getUserId(), form.getUserName());
        if (password.equals("")) {
            log.info("일치하는 회원이 없습니다.");
            throw new NullPointerException("password not found");
        } else {
            return ResponseEntity.ok(password);
        }
    }

    //문자 인증
    @PostMapping("/users/join/sms")
    public void smsAuth(@Valid @RequestBody PhoneNumberForm form, HttpSession session){
        session.setAttribute("verifyNumber", String.valueOf((int)(Math.random()*1000)));
        messageService.sendMessage(form.getPhoneNumber(), String.valueOf(session.getAttribute("verifyNumber")));
    }

    @PostMapping("/users/join/verify")
    public void smsVerify(@Valid @RequestBody PhoneVerifyForm form, HttpSession session){
        String verifyNumber = String.valueOf(session.getAttribute("verifyNumber"));
        if (verifyNumber.equals(form.getVerify())) {
            log.info("인증 완료"); //추후 기능 추가
            session.removeAttribute("verifyNumber");
        }
        else log.info("인증번호 불일치"); //추후 기능 추가
    }

    @PostMapping("/users/join/email")
    public void sendEmail(@Valid @RequestBody EmailForm form, HttpSession session) throws MessagingException {
        String authNum = emailService.sendEmail(form.getEmail()); //인증 메일 전송 후, 인증번호 리턴
        session.setAttribute("authNum", authNum); //인증번호 세션에 저장
    }

    @PostMapping("/users/join/email/auth")
    public void emailAuth(@RequestBody EmailAuthForm form, HttpSession session){
        String enterNum = form.getAuthNum(); //사용자가 입력한 인증번호
        String authNum = String.valueOf(session.getAttribute("authNum"));

        if (enterNum.equals(authNum))
            log.info("인증 완료"); //작업 필요
        else log.info("인증번호 불일치"); //작업 필요
    }
}