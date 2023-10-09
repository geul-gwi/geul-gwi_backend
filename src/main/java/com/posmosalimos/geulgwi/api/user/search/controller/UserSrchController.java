package com.posmosalimos.geulgwi.api.user.search.controller;

import com.posmosalimos.geulgwi.api.user.search.dto.UserListDTO;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserSrchController {

    private final UserService userService;

    @PostMapping("/list")
    public ResponseEntity<List> list() {

        List<UserListDTO> listDtos = userService.findUserInfos();

        return ResponseEntity.ok(listDtos);
    }

    @PostMapping("/detail/{seq}")
    public ResponseEntity<UserInfoDTO> detail(@PathVariable("seq") Long seq) {

        User findUser = userService.findBySeq(seq);

        return ResponseEntity.ok(UserInfoDTO.builder()
                .userSeq(findUser.getUserSeq())
                .userId(findUser.getUserId())
                .nickname(findUser.getNickname())
                .profile(findUser.getUserProfile())
                .role(findUser.getRole())
                .comment(findUser.getComment())
                .userPassword(findUser.getPassword())
                .tag1(findUser.getTag1())
                .tag2(findUser.getTag2())
                .tag3(findUser.getTag3())
                .profile(findUser.getUserProfile())
                .build()
        );

    }
}
