package com.posmosalimos.geulgwi.api.user.join.controller;

import com.posmosalimos.geulgwi.api.user.join.service.JoinService;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    .tag1(joinDTO.getTag1())
                    .tag2(joinDTO.getTag2())
                    .tag3(joinDTO.getTag3())
                    .profile(joinDTO.getUserProfile())
                    .role(Role.COMMON)
                    .build();

        joinService.join(createUser);

        return ResponseEntity.ok(true);
    }
}
