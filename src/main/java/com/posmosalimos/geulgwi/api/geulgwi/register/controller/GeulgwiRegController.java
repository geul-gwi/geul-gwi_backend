package com.posmosalimos.geulgwi.api.geulgwi.register.controller;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.api.geulgwi.register.service.GeulgwiRegService;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/geulgwi")
public class GeulgwiRegController {

    private final TokenManager tokenManager;
    private final UserService userService;
    private final FileService fileService;
    private final GeulgwiRegService geulgwiRegService;


    @PostMapping(value = "/register/{userSeq}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> register(@RequestPart(value = "geulgwiRegDTO") GeulgwiRegDTO geulgwiRegDTO,
                                            @RequestPart(value = "files") List<MultipartFile> files,
                                            @PathVariable("userSeq") Long seq,
                                            HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        User findUser = userService.findBySeq(seq);

        List<String> storeFiles = fileService.storeFiles(files);

        geulgwiRegService.register(geulgwiRegDTO, findUser, storeFiles);

        return ResponseEntity.ok(true);
    }
}