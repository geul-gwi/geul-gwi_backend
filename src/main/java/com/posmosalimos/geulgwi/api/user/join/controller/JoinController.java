package com.posmosalimos.geulgwi.api.user.join.controller;

import com.posmosalimos.geulgwi.api.tag.list.dto.TagDTO;
import com.posmosalimos.geulgwi.api.user.join.service.JoinService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.api.user.join.dto.JoinDTO;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class JoinController {

    private final JoinService joinService;
    private final TagService tagService;

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
