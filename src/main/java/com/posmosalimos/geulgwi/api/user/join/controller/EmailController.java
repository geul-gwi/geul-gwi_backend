package com.posmosalimos.geulgwi.api.user.join.controller;

import com.posmosalimos.geulgwi.api.user.join.dto.EmailDTO;
import com.posmosalimos.geulgwi.api.user.join.service.EmailService;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Slf4j
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    @PostMapping("/valid")
    public ResponseEntity<Boolean> validEmail(@RequestBody EmailDTO emailDTO) {

        emailService.sendMail(emailDTO.getEmail());
        log.info("user - email sending");

        return ResponseEntity.ok(true);
    }

    @PostMapping("/valid/code")
    public ResponseEntity<Boolean> validCode(@RequestBody EmailDTO.Valid valid) {

        emailService.validNumber(valid.getCode());
        log.info("user - email verification");

        return ResponseEntity.ok(true);
    }

    @PostMapping("/id")
    public ResponseEntity<Boolean> findId(@RequestBody EmailDTO emailDTO) {
        String userId = userService.findByEmail(emailDTO.getEmail());

        if (userId.equals("true"))
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);

        emailService.findId(userId, emailDTO.getEmail());
        log.info("user - find id({})", userId);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/password")
    public ResponseEntity<Boolean> findPassword(@RequestBody EmailDTO.Request emailDTO) {

        List<String> info = userService.findByIdAndEmail(emailDTO.getUserId(), emailDTO.getEmail());

        emailService.findPassword(info.get(0), info.get(1), emailDTO.getEmail());
        log.info("user - find password({})", info.get(1));

        return ResponseEntity.ok(true);
    }

}
