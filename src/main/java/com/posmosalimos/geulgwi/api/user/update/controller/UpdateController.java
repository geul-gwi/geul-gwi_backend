package com.posmosalimos.geulgwi.api.user.update.controller;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.api.user.update.service.UpdateService;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UpdateController {

    private final UpdateService updateService;
    private final FileService fileService;
    private final TokenManager tokenManager;

    @PostMapping(value = "/update/{seq}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> updateUser(@RequestPart(value = "updateDTO") UpdateDTO.Request updateDTO,
                                             @RequestPart(value = "file", required = false) MultipartFile file,
                                             @PathVariable("seq") Long userSeq,
                                             HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String accessToken = authorization.split(" ")[1];
        // 토큰 유효성 체크
        tokenManager.validateToken(accessToken);

        User user = updateService.update(userSeq, updateDTO);
        fileService.storeUserFile(user, file);
        log.info("user - update success(userSeq: {})", userSeq);

        return ResponseEntity.ok(true);
    }
}
