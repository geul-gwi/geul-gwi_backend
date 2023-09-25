package com.posmosalimos.geulgwi.api.user.join.controller;

import com.posmosalimos.geulgwi.api.user.join.dto.EmailDTO;
import com.posmosalimos.geulgwi.api.user.join.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/valid")
    public ResponseEntity<String> validEmail(@RequestBody EmailDTO dto){

        int number = emailService.sendMail(dto.getEmail());

        String num = String.valueOf(number);

        return ResponseEntity.ok(num);
    }

    @PostMapping("/valid/code")
    public ResponseEntity<Boolean> validCode(@RequestBody EmailDTO.Valid valid){

        return ResponseEntity.ok(emailService.validNumber(valid.getCode()));
    }

}
