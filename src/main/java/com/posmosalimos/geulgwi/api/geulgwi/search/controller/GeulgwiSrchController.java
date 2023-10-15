package com.posmosalimos.geulgwi.api.geulgwi.search.controller;

import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiListDTO;
import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiSrchDTO;
import com.posmosalimos.geulgwi.api.geulgwi.search.service.GeulgwiSrchService;
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

    @GetMapping("/search/{geulgwiSeq}")
    public ResponseEntity<GeulgwiSrchDTO.Response> search(@PathVariable("geulgwiSeq") Long geulgwiSeq) {

        GeulgwiSrchDTO.Response searchDto = geulgwiSrchService.search(geulgwiSeq);
        log.info("geulgwi - search(geulgwiSeq: {})", geulgwiSeq);

        return ResponseEntity.ok(searchDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List> list() {
        List<GeulgwiListDTO> geulgwiListDTOS = geulgwiSrchService.list();
        log.info("geulgwi - list");

        return ResponseEntity.ok(geulgwiListDTOS);

    }
}
