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


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tag")
public class RegTagController {

    private final TokenManager tokenManager;
    private final RegTagService createTagService;

    @PostMapping("/register/{userSeq}")
    public ResponseEntity<RegTagDTO.Response> create(@RequestBody RegTagDTO regTagDTO,
                                          @PathVariable("userSeq") Long userSeq,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        Tag tag = createTagService.create(regTagDTO, userSeq);
        log.info("tag - register success");

        return ResponseEntity.ok(
                RegTagDTO.Response.builder()
                        .tagSeq(tag.getTagSeq())
                        .backColor(tag.getBackColor())
                        .fontColor(tag.getFontColor())
                        .value(tag.getValue())
                        .type(tag.getType().toString())
                        .build());
    }
}