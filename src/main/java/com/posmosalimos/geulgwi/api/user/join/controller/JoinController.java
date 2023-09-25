package com.posmosalimos.geulgwi.api.user.join.controller;

import com.posmosalimos.geulgwi.api.user.join.service.JoinService;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
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

        User createUser = User.builder()
                    .userId(joinDTO.getUserId())
                    .password(joinDTO.getUserPassword())
                    .userName(joinDTO.getUserName())
                    .nickname(joinDTO.getUserNickname())
                    .gender(joinDTO.getUserGender())
                    .age(joinDTO.getUserAge())
                    .tag1(joinDTO.getUserTags().get(0))
                    .tag2(joinDTO.getUserTags().get(1))
                    .tag3(joinDTO.getUserTags().get(2))
                    .profile(joinDTO.getUserProfile())
                    .role(Role.COMMON)
                    .build();

        joinService.join(createUser);

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
