package com.posmosalimos.geulgwi.api.geulgwi.update.controller;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.api.geulgwi.update.service.GeulgwiUpdtService;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.like.service.LikeService;
import com.posmosalimos.geulgwi.api.notice.service.NoticeService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/geulgwi")
@Slf4j
@RequiredArgsConstructor
public class GeulgwiUpdtController {

    private final TokenManager tokenManager;
    private final GeulgwiUpdtService geulgwiUpdtService;
    private final LikeService likeService;
    private final NoticeService noticeService;

    @PostMapping(value = "/update/{geulgwiSeq}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> update(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                          @RequestPart("geulgwiRegDTO") GeulgwiRegDTO geulgwiRegDTO,
                                          @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                          HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        geulgwiUpdtService.update(geulgwiSeq, geulgwiRegDTO, files);
        log.info("geulgwi - update (geulgwiSeq: {})", geulgwiSeq);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/like/{geulgwiSeq}/{userSeq}")
    public ResponseEntity<Boolean> like(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                         @PathVariable("userSeq") Long userSeq,
                                         HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        Likes likes = likeService.likeGeulgwi(geulgwiSeq, userSeq);
        noticeService.sendByLikeGeulgwi(likes);

        log.info("geulgwi - like (userSeq: {})", userSeq);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/unlike/{geulgwiSeq}/{userSeq}")
    public ResponseEntity<Boolean> unlike(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                          @PathVariable("userSeq") Long userSeq,
                                          HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        likeService.unlikeGeulgwi(geulgwiSeq, userSeq);
        log.info("geulgwi - unlike success(userSeq: {})", userSeq);

        return ResponseEntity.ok(true);
    }

}
