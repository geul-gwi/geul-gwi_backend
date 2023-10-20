package com.posmosalimos.geulgwi.api.message.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    @PostMapping("/send/{userSeq}")
    public ResponseEntity<Boolean> send() {


        return ResponseEntity.ok(true);
    }
}
