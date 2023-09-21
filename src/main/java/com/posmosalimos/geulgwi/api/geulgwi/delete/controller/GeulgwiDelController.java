package com.posmosalimos.geulgwi.api.geulgwi.delete.controller;

import com.posmosalimos.geulgwi.api.geulgwi.delete.service.GeulgwiDelService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/geulgwi")
public class GeulgwiDelController {

    private final GeulgwiDelService geulgwiDelService;
    private final TokenManager tokenManager;

    @DeleteMapping("/delete/{userSeq}/{geulgwiSeq}")
    public ResponseEntity<Boolean> delete(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                          @PathVariable("userSeq") Long userSeq,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        geulgwiDelService.delete(geulgwiSeq, userSeq);

        return ResponseEntity.ok(true);
    }
}
