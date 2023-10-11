package com.posmosalimos.geulgwi.api.tag.list.controller;

import com.posmosalimos.geulgwi.api.tag.list.dto.TagDTO;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagListController {

    private final TagService tagService;

    @PostMapping("/admin/list")
    public ResponseEntity<List<TagDTO>> list() {
        List<Tag> tags = tagService.findAll();

        List<TagDTO> response = tags.stream()
                .map(TagDTO::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
