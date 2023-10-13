package com.posmosalimos.geulgwi.api.user.join.controller;

import com.posmosalimos.geulgwi.api.user.join.service.JoinService;
import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<Boolean> join(@RequestBody JoinDTO joinDTO) {

        joinService.join(joinDTO);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/validate/{userId}")
    public ResponseEntity<Boolean> validateId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(joinService.validateDuplicateUserId(userId));
    }

    @PostMapping("/validate/nickname/{nickname}")
    public ResponseEntity<Boolean> validateNickname(@PathVariable("nickname") String nickname) {

        return ResponseEntity.ok(joinService.validateDuplicateNickname(nickname));
    }

}