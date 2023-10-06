package com.posmosalimos.geulgwi.api.user.list.controller;

import com.posmosalimos.geulgwi.api.user.list.dto.UserListDTO;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserListController {

    private final UserService userService;

    @PostMapping("/list")
    public ResponseEntity<List> list() {

        List<UserListDTO> listDtos = userService.findUserInfos();

        return ResponseEntity.ok(listDtos);
    }

}
