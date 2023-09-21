package com.posmosalimos.geulgwi.api.geulgwi.delete.controller;

import com.posmosalimos.geulgwi.api.geulgwi.delete.service.GeulgwiDelService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/geulgwi")
public class GeulgwiDelController {

    private final GeulgwiDelService geulgwiDelService;
    private final TokenManager tokenManager;

    @PostMapping("/delete/{geulgwiSeq}")
    public ResponseEntity<Boolean> delete(@PathVariable("geulgwiSeq") Long seq,
                                          HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        geulgwiDelService.delete(seq);

        return ResponseEntity.ok(true);
    }
}
