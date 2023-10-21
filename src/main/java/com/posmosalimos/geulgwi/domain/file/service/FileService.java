package com.posmosalimos.geulgwi.domain.file.service;

import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.file.repository.FileRepository;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class FileService {

    @Value("${file.dir}")
    private String fileDir;
    private final FileRepository fileRepository;

    @Transactional
    public void storeGeulgwiFiles(Geulgwi geulgwi, List<MultipartFile> multipartFiles) throws IOException {
        //글귀 파일 등록
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String originalFilename = multipartFile.getOriginalFilename(); //원래 파일명
                String storeFilename = createStoreFileName(originalFilename); //디비 저장용 파일명
                multipartFile.transferTo(new File(getFullPath(storeFilename))); //로컬에 uuid를 파일명으로 저장

                UploadFile file = UploadFile.builder()
                        .upload(originalFilename)
                        .store(getFullPath(storeFilename)) //패스 + 파일명
                        .geulgwi(geulgwi)
                        .build();

                fileRepository.save(file);
            }
        }

    }

    @Transactional
    public void removeGeulgwiFiles(Geulgwi geulgwi) {
        //글귀 파일 삭제
        fileRepository.deleteByGeulgwi(geulgwi);
    }

    @Transactional
    public void storeUserFile(User user, MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty())
            throw new BusinessException(ErrorCode.MULTIPART_FILE_NOT_FOUND);

        String originalFilename = multipartFile.getOriginalFilename(); //원래 파일명
        String storeFilename = createStoreFileName(originalFilename); //디비 저장용 파일명
        multipartFile.transferTo(new File(getFullPath(storeFilename))); //로컬에 uuid를 파일명으로 저장

        UploadFile file = UploadFile.builder()
                .upload(originalFilename)
                .store(getFullPath(storeFilename)) //패스 + 파일명
                .user(user)
                .build();

        fileRepository.save(file);
    }

    public String getFullPath(String filename) {
        //경로 + 서버용 파일명
        return fileDir + filename;
    }

    public String createStoreFileName(String originalFilename) {
        //서버에 저장하는 파일명
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        //확장자명 추출
        int pos = originalFilename.lastIndexOf("."); //위치
        return originalFilename.substring(pos + 1);
    }

    public String findByUserSeq(User user) {
        UploadFile profile = fileRepository.findByUser(user);

        return profile == null ? null : profile.getStore();
    }

}
