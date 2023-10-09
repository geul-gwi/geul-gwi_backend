package com.posmosalimos.geulgwi.api.tag.register.controller;

import com.posmosalimos.geulgwi.api.tag.register.dto.RegTagDTO;
import com.posmosalimos.geulgwi.api.tag.register.service.RegTagService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tag")
public class RegTagController {

    private final TokenManager tokenManager;
    private final RegTagService createTagService;

    @PostMapping("/register/{seq}")
    public ResponseEntity<Boolean> create(@RequestBody RegTagDTO regTagDTO,
                                          @PathVariable("seq") Long seq,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        createTagService.create(regTagDTOS, seq);

        return ResponseEntity.ok(true);

    }
}