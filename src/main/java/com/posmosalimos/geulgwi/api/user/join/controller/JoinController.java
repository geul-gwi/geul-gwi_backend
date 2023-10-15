package com.posmosalimos.geulgwi.api.user.join.controller;

import com.posmosalimos.geulgwi.api.user.join.service.JoinService;
import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<Boolean> join(@RequestBody JoinDTO joinDTO) {

        joinService.join(joinDTO);
        log.info("user - join success");

        return ResponseEntity.ok(true);
    }

    @PostMapping("/validate/{userId}")
    public ResponseEntity<Boolean> validateId(@PathVariable("userId") String userId) {
        joinService.validateDuplicateUserId(userId);
        log.info("user - ID duplicate check success");

        return ResponseEntity.ok(true);
    }

    @PostMapping("/validate/nickname/{nickname}")
    public ResponseEntity<Boolean> validateNickname(@PathVariable("nickname") String nickname) {
        joinService.validateDuplicateNickname(nickname);
        log.info("user - nickname duplicate check success");

        return ResponseEntity.ok(true);
    }

}