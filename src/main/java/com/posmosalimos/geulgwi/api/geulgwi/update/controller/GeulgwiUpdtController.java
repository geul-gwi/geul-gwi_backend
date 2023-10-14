package com.posmosalimos.geulgwi.api.geulgwi.update.controller;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.api.geulgwi.update.service.GeulgwiUpdtService;
import com.posmosalimos.geulgwi.domain.like.service.LikeService;
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
    private final GeulgwiUpdtService geulgwiUdtService;
    private final LikeService likeService;

    @PostMapping(value = "/update/{geulgwiSeq}")
    public ResponseEntity<Boolean> update(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                          @RequestBody GeulgwiRegDTO geulgwiRegDTO,
                                          HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        geulgwiUdtService.update(geulgwiSeq, geulgwiRegDTO);

        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/update/file/upload/{geulgwiSeq}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> uploadFiles(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                               @RequestPart(value = "files") List<MultipartFile> files,
                                               HttpServletRequest httpServletRequest) throws IOException {
        //파일 등록(복수건)

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        geulgwiUdtService.uploadFiles(geulgwiSeq, files);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/update/file/remove/{geulgwiSeq}")
    public ResponseEntity<Boolean> removeFiles(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                                HttpServletRequest httpServletRequest) {
        //파일 삭제(전체)

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        geulgwiUdtService.removeFiles(geulgwiSeq);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/like/{geulgwiSeq}/{userSeq}")
    public ResponseEntity<Boolean> like(@PathVariable("geulgwiSeq") Long geulgwiSeq,
                                         @PathVariable("userSeq") Long userSeq,
                                         HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        likeService.likeGeulgwi(geulgwiSeq, userSeq);

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

        return ResponseEntity.ok(true);
    }

}
