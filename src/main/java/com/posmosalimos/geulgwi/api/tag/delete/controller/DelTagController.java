package com.posmosalimos.geulgwi.api.tag.delete.controller;

import com.posmosalimos.geulgwi.api.tag.delete.service.DelTagService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@Slf4j
public class DelTagController {

    private final DelTagService delTagService;
    private final TokenManager tokenManager;

    @DeleteMapping("/delete/{tagSeq}")
    public ResponseEntity<Boolean> delete(@PathVariable("tagSeq") Long tagSeq,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");

        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        delTagService.delete(tagSeq);
        log.info("tag - delete success");
        return ResponseEntity.ok(true);
    }
}
