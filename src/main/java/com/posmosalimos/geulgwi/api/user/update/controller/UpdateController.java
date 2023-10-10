package com.posmosalimos.geulgwi.api.user.update.controller;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.api.user.update.service.UpdateService;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfo;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UpdateController {

    private final UpdateService updateService;
    private final FileService fileService;
    private final TokenManager tokenManager;

    @PostMapping(value = "/update/{seq}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserInfoDTO> updateUser(@RequestPart(value = "updateDTO") UpdateDTO.Request updateDTO,
                                             @RequestPart(value = "file") MultipartFile file,
                                             @PathVariable("seq") Long userSeq,
                                             HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String accessToken = authorization.split(" ")[1];
        // 토큰 유효성 체크
        tokenManager.validateToken(accessToken);

        String storeFile = fileService.storeFile(file);
        UserInfoDTO userInfoDTO = updateService.update(userSeq, updateDTO, storeFile);

        return ResponseEntity.ok(userInfoDTO);
    }
}
