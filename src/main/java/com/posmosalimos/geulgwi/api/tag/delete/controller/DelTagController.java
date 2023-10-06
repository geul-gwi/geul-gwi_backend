package com.posmosalimos.geulgwi.api.tag.delete.controller;

import com.posmosalimos.geulgwi.api.tag.delete.service.DelTagService;
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

    @DeleteMapping("/delete/{value}")
    public ResponseEntity<Boolean> delete(@PathVariable("value") String value) {
        delTagService.delete(value);
        return ResponseEntity.ok(true);
    }
}