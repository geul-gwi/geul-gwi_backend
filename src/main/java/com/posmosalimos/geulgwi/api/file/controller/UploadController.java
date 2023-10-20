package com.posmosalimos.geulgwi.api.file.controller;

import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/file")
@Slf4j
@RequiredArgsConstructor
public class UploadController {

    private final TokenManager tokenManager;

    @GetMapping(value = "/{file}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> upload(@PathVariable("file") String file,
                                         HttpServletRequest httpServletRequest) throws IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        InputStream imageStream = new FileInputStream(file); //이미지 읽어오기
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(); //임시로 저장할 ByteArrayOutputStream 생성
        int read;
        byte[] imageByteArray = new byte[imageStream.available()]; //이미지 파일의 크기만큼 바이트 배열을 생성

        while ((read = imageStream.read(imageByteArray, 0, imageByteArray.length)) != -1) { //바이트 배열에 저장
            buffer.write(imageByteArray, 0, read);
        }
        buffer.flush();
        byte[] targetArray = buffer.toByteArray();
        imageStream.close();

        return new ResponseEntity<byte[]>(targetArray, HttpStatus.OK); //바이트 배열 리턴
    }

}

