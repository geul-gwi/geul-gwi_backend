package com.posmosalimos.geulgwi.api.user.join.controller;

import com.posmosalimos.geulgwi.api.user.join.service.JoinService;
import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class JoinController {

    private final JoinService joinService;

    @PostMapping(value = "/join", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> join(@RequestPart(value = "joinDTO") JoinDTO joinDTO,
                                        @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        joinService.join(joinDTO, file);
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