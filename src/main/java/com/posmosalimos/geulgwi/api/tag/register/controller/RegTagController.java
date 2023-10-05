package com.posmosalimos.geulgwi.api.tag.register.controller;

import com.posmosalimos.geulgwi.api.tag.register.dto.RegTagDTO;
import com.posmosalimos.geulgwi.api.tag.register.service.RegTagService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tag")
public class RegTagController {

    private final TokenManager tokenManager;
    private final RegTagService createTagService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> create(@RequestBody List<RegTagDTO> createTagDTOList,
                                          HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<Tag> tags = new ArrayList<>();

        for (RegTagDTO tag : createTagDTOList) {
            Tag dto = Tag.builder()
                    .backColor(tag.getBackColor())
                    .fontColor(tag.getFontColor())
                    .value(tag.getValue())
                    .build();

            tags.add(dto);
        }

        createTagService.create(tags);

        return ResponseEntity.ok(true);

    }
}