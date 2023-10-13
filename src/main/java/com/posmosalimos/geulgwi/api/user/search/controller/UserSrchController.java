package com.posmosalimos.geulgwi.api.user.search.controller;

import com.posmosalimos.geulgwi.api.user.search.dto.UserListDTO;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        UserInfoDTO userInfo = userService.findUserInfo(seq);

        return ResponseEntity.ok(userInfo);
    }
}
