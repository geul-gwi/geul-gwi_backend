package com.posmosalimos.geulgwi.api.geulgwi.register.controller;

import com.posmosalimos.geulgwi.api.friend.search.service.FriendSrchService;
import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.api.geulgwi.register.service.GeulgwiRegService;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
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

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/geulgwi")
public class GeulgwiRegController {

    private final TokenManager tokenManager;
    private final GeulgwiRegService geulgwiRegService;
    private final NoticeService noticeService;
    private final FriendSrchService friendSrchService;

    @PostMapping(value = "/register/{userSeq}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> register(@RequestPart(value = "geulgwiRegDTO") GeulgwiRegDTO geulgwiRegDTO,
                                            @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                            @PathVariable("userSeq") Long seq,
                                            HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        Geulgwi geulgwi = geulgwiRegService.register(geulgwiRegDTO, seq, files);
        log.info("geulgwi - register (userSeq: {})", seq);

        List<Long> subscribers = friendSrchService.findSubscribers(geulgwi.getUser()); //작성자의 구독자들 리스트화
        noticeService.sendByGeulgwi(geulgwi.getGeulgwiSeq(), geulgwi.getUser().getUserSeq(), subscribers); //구독자들에게 알림 전송

        return ResponseEntity.ok(true);
    }

}