package com.posmosalimos.geulgwi.api.geulgwi.search.controller;

import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiListDTO;
import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiSrchDTO;
import com.posmosalimos.geulgwi.api.geulgwi.search.service.GeulgwiSrchService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geulgwi")
@Slf4j
@RequiredArgsConstructor
public class GeulgwiSrchController {

    private final GeulgwiSrchService geulgwiSrchService;
    private final TokenManager tokenManager;

    @GetMapping("/search/{geulgwiSeq}/{userSeq}")
    public ResponseEntity<GeulgwiSrchDTO.Response> search(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                                          @PathVariable("userSeq") Long userSeq,
                                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];
        tokenManager.validateToken(accessToken);

        GeulgwiSrchDTO.Response searchDto = geulgwiSrchService.search(geulgwiSeq, userSeq);
        log.info("geulgwi - search(geulgwiSeq: {})", geulgwiSeq);

        return ResponseEntity.ok(searchDto);
    }

    @GetMapping("/list/{userSeq}") //특정 회원이 쓴 글 리스트
    public ResponseEntity<List> listByUserSeq(@PathVariable("userSeq") Long userSeq,
                                              HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<GeulgwiSrchDTO.Response> geulgwiSrchDTOS = geulgwiSrchService.listByUserSeq(userSeq);

        return ResponseEntity.ok(geulgwiSrchDTOS);
    }

    @GetMapping("/list")
    public ResponseEntity<List> listAll(HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<GeulgwiListDTO> geulgwiListDTOS = geulgwiSrchService.listAll();

        log.info("geulgwi - list");

        return ResponseEntity.ok(geulgwiListDTOS);

    }


}
