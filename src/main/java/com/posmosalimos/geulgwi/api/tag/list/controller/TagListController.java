package com.posmosalimos.geulgwi.api.tag.list.controller;

import com.posmosalimos.geulgwi.api.tag.list.dto.TagDTO;
import com.posmosalimos.geulgwi.domain.tag.constant.TagType;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<TagDTO>> listAll() {
        List<Tag> tags = tagService.findAll();

        List<TagDTO> response = tags.stream()
                .map(TagDTO::from)
                .collect(Collectors.toList());
        log.info("tag - list");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/list/{tagType}")
    public ResponseEntity<List<TagDTO>> listByDefaultType(@PathVariable("tagType") String tagType) {
        List<Tag> tags = tagService.findByType(tagType);

        List<TagDTO> tagDTOS = tags.stream()
                .map(TagDTO::from)
                .collect(Collectors.toList());
        log.info("tag - list(default)");

        return ResponseEntity.ok(tagDTOS);
    }
}
