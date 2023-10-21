package com.posmosalimos.geulgwi.api.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UploadController {

    @GetMapping(value = "/file", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> upload(@RequestParam String file) throws IOException {

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

