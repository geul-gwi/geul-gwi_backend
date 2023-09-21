package com.posmosalimos.geulgwi.api.geulgwi.update.controller;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.api.geulgwi.update.service.GeulgwiUdtService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geulgwi")
@Slf4j
@RequiredArgsConstructor
public class GeulgwiUdtController {

    private final GeulgwiUdtService geulgwiUdtService;
    private final TokenManager tokenManager;

    @PostMapping("/update/{userSeq}/{geulgwiSeq}")
    public ResponseEntity<Boolean> update(@PathVariable("userSeq") Long userSeq,
                                          @PathVariable("geulgwiSeq") Long geulgwiSeq,
                                          @Valid @RequestBody GeulgwiRegDTO geulgwiRegDTO,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        geulgwiUdtService.update(userSeq, geulgwiSeq, geulgwiRegDTO);

        return ResponseEntity.ok(true);
    }




}
