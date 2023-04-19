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

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.info("Home Page(none login)");
            return "index";
        }

        Users loginUser = (Users) session.getAttribute(SessionConst.LOGIN_USER);

        if (loginUser == null) {
            log.info("Home Page(none login)");
            return "index";
        }

        model.addAttribute("loginUser", loginUser);
        log.info("Home Page(login)");
        return "loginIndex";
    }
}
