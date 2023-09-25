package com.posmosalimos.geulgwi.api.geulgwi.update.controller;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.api.geulgwi.update.service.GeulgwiUpdtService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

@RestController
@RequestMapping("/geulgwi")
@Slf4j
@RequiredArgsConstructor
public class GeulgwiUpdtController {

    private final GeulgwiUpdtService geulgwiUdtService;
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

    @PostMapping("/likes/{geulgwiSeq}")
    public ResponseEntity<Boolean> likes(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                         HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

//        geulgwiUdtService.likes(geulgwiSeq);

        return ResponseEntity.ok(true);
    }


}
