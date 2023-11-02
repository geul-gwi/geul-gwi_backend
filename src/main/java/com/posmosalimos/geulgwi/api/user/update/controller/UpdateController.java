package com.posmosalimos.geulgwi.api.user.update.controller;

import com.posmosalimos.geulgwi.api.user.update.dto.UpdateDTO;
import com.posmosalimos.geulgwi.api.user.update.service.UpdateService;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
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

    @PostMapping(value = "/update/{seq}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> updateUser(@RequestPart(value = "updateDTO") UpdateDTO.Request updateDTO,
                                             @RequestPart(value = "file", required = false) MultipartFile file,
                                             @PathVariable("seq") Long userSeq,
                                             HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        String accessToken = authorization.split(" ")[1];
        //토큰 유효성 체크
        tokenManager.validateToken(accessToken);

        User user = updateService.update(userSeq, updateDTO);

        if (fileService.findByUser(user) != null && file != null && !file.isEmpty()) {
            //원래 사진이 저장되어 있었고 새로운 사진으로 update
            fileService.removeUserFile(user);
            fileService.storeUserFile(user, file);
        } else if (file != null && !file.isEmpty())
            //사진 저장을 한 적이 없었고 새로운 사진으로 update
            fileService.storeUserFile(user, file);
        else System.out.println("??????????");

        log.info("user - update success(userSeq: {})", userSeq);

        return ResponseEntity.ok(true);
    }
}
