package com.posmosalimos.geulgwi.api.geulgwi.update.controller;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.api.geulgwi.update.service.GeulgwiUpdtService;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/geulgwi")
@Slf4j
@RequiredArgsConstructor
public class GeulgwiUpdtController {

    private final TokenManager tokenManager;
    private final GeulgwiUpdtService geulgwiUdtService;
    private final FileService fileService;

    @PostMapping(value = "/update/{userSeq}/{geulgwiSeq}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> update(@PathVariable("userSeq") Long userSeq,
                                          @PathVariable("geulgwiSeq") Long geulgwiSeq,
                                          @RequestPart(value = "geulgwiRegDTO") GeulgwiRegDTO geulgwiRegDTO,
                                          @RequestPart(value = "files") List<MultipartFile> files,
                                          HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        List<String> storeFiles = fileService.storeFiles(files);

        geulgwiUdtService.update(userSeq, geulgwiSeq, geulgwiRegDTO, storeFiles);

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
