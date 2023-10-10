package com.posmosalimos.geulgwi.api.user.update.controller;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.api.user.update.service.UpdateService;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UpdateController {

    private final UpdateService updateService;
    private final FileService fileService;
    private final TokenManager tokenManager;
    private final TagService tagService;

    @PostMapping(value = "/update/{seq}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> updateUser(@RequestPart(value = "updateDTO") UpdateDTO.Request updateDTO,
                                             @RequestPart(value = "file") MultipartFile file,
                                             @PathVariable("seq") Long userSeq,
                                             HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String accessToken = authorization.split(" ")[1];
        // 토큰 유효성 체크
        tokenManager.validateToken(accessToken);

        String storeFile = fileService.storeFile(file);
        updateService.update(userSeq, updateDTO, storeFile);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/update/tags")
    public UpdateDTO.Response tags() {
        List<Tag> tags = tagService.findAll();

        return UpdateDTO.Response.builder().tags(tags).build();
    }
}
