package com.posmosalimos.geulgwi.controller;

import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        //GetMapping 한 직후 세션값을 불러오는 것을 막음 -> User정보 보호
        if (session == null) {
            log.info("Home Page(none login)");
            return "index";
        }

        Users loginUser = (Users) model.getAttribute(SessionConst.LOGIN_USER);
        System.out.println(SessionConst.LOGIN_USER);
        if (loginUser == null) {
            log.info("Home Page(none login)");
            return "index";
        }

        model.addAttribute("loginUser", loginUser);
        log.info("Home Page(login)");
        return "loginIndex";
    }

}
