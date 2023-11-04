package com.posmosalimos.geulgwi.api.tag.search.controller;

import com.posmosalimos.geulgwi.api.tag.search.dto.TagDTO;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagSrchController {

    private final TagService tagService;
    private final TokenManager tokenManager;

    @PostMapping("/admin/list")
    public ResponseEntity<List<TagDTO>> listAll(HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");

        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

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
        log.info("tag - list(tagType: {})", tagType);

        return ResponseEntity.ok(tagDTOS);
    }
}
