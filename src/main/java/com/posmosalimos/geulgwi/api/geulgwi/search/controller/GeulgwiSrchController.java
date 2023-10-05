package com.posmosalimos.geulgwi.api.geulgwi.search.controller;

import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiSrchDTO;
import com.posmosalimos.geulgwi.api.geulgwi.search.service.GeulgwiSrchService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geulgwi")
@Slf4j
@RequiredArgsConstructor
public class GeulgwiSrchController {

    private final GeulgwiSrchService geulgwiSrchService;
    private final TokenManager tokenManager;

    @GetMapping("/search/{geulgwiSeq}")
    public ResponseEntity<GeulgwiSrchDTO.Response> search(@PathVariable("geulgwiSeq") Long geulgwiSeq) {

        GeulgwiSrchDTO.Response searchDto = geulgwiSrchService.search(geulgwiSeq);

        return ResponseEntity.ok(searchDto);
    }
}
