package com.posmosalimos.geulgwi.api.geulgwi.search.service;

import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiListDTO;
import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiSrchDTO;
import com.posmosalimos.geulgwi.api.tag.search.dto.TagDTO;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.GeulgwiTag;
import com.posmosalimos.geulgwi.domain.geulgwi.repository.GeulgwiTagRepository;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.like.service.LikeService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class GeulgwiSrchService {

    private final GeulgwiService geulgwiService;
    private final GeulgwiTagRepository geulgwiTagRepository;
    private final LikeService likeService;
    private final UserService userService;

    public GeulgwiSrchDTO.Response search(Long geulgwiSeq, Long viewSeq) {

        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);
        User findUser = userService.findBySeq(viewSeq); //현재 글을 보는 유저

        boolean isLiked = likeService.findByGeulgwi(findGeulgwi, findUser); //현재 글을 보는 사람이 좋아요를 눌렀는지

        List<String> storeFiles = findGeulgwi.getUploadFiles().stream()
                .filter(file -> file.getStore() != null).toList()
                .stream().map(UploadFile::getStore).collect(Collectors.toList());

        List<TagDTO> tags = findGeulgwi.getGeulgwiTags().stream()
                .filter(tag -> tag.getTag() != null).toList()
                .stream().map(GeulgwiTag::getTag).toList()
                .stream().map(tag -> TagDTO.from(tag)).toList();

        return GeulgwiSrchDTO.Response.builder()
                .geulgwiSeq(findGeulgwi.getGeulgwiSeq())
                .geulgwiContent(findGeulgwi.getGeulgwiContent())
                .userSeq(findGeulgwi.getUser().getUserSeq())
                .nickname(findGeulgwi.getUser().getNickname())
                .comment(findGeulgwi.getUser().getComment())
                .userProfile(Optional.ofNullable(findGeulgwi.getUser().getUploadFile())
                        .map(UploadFile::getStore)
                        .orElse(null))
                .regDate(findGeulgwi.getRegDate())
                .likeCount(findGeulgwi.getLikes().size())
                .files(storeFiles)
                .isLiked(isLiked)
                .tags(tags)
                .build();
    }

    public List<GeulgwiSrchDTO.Response> findByUserSeq(Long userSeq, Long viewSeq) {
        List<Geulgwi> list = geulgwiService.findByUserSeq(userSeq);
        List<GeulgwiSrchDTO.Response> dtos = new ArrayList<>();
        User viewUser = userService.findBySeq(viewSeq);

        for (Geulgwi geulgwi : list) {
            List<String> storeFiles = geulgwi.getUploadFiles().stream()
                    .filter(file -> file.getStore() != null).toList()
                    .stream().map(UploadFile::getStore).collect(Collectors.toList());

            List<TagDTO> tags = geulgwi.getGeulgwiTags().stream()
                    .filter(tag -> tag.getTag() != null).toList()
                    .stream().map(GeulgwiTag::getTag).toList()
                    .stream().map(tag -> TagDTO.from(tag)).toList();

            boolean isLiked = likeService.findByGeulgwi(geulgwi, viewUser);

            dtos.add(
                    GeulgwiSrchDTO.Response.builder()
                            .geulgwiSeq(geulgwi.getGeulgwiSeq())
                            .geulgwiContent(geulgwi.getGeulgwiContent())
                            .userSeq(geulgwi.getUser().getUserSeq())
                            .regDate(geulgwi.getRegDate())
                            .likeCount(geulgwi.getLikes().size())
                            .files(storeFiles)
                            .isLiked(isLiked)
                            .tags(tags)
                            .build());

        }

        return dtos;
    }

    public List<GeulgwiListDTO> findAll() {
        List<Geulgwi> list = geulgwiService.listAll();

        return list.stream()
                .map(geulgwi -> GeulgwiListDTO.builder()
                        .geulgwiSeq(geulgwi.getGeulgwiSeq())
                        .userSeq(geulgwi.getUser().getUserSeq())
                        .nickname(geulgwi.getUser().getNickname())
                        .geulgwiContent(geulgwi.getGeulgwiContent())
                        .regDate(geulgwi.getRegDate())
                        .build()).toList();

    }

    public List<GeulgwiListDTO> findByTagSeq(Long tagSeq) {
        List<GeulgwiTag> geulgwiTagList = geulgwiTagRepository.findByTagSeq(tagSeq);

        return geulgwiTagList.stream()
                .map(geulgwiTag -> GeulgwiListDTO.from(geulgwiTag.getGeulgwi()))
                .toList();
    }

    public List<TagDTO.Response> getTrend() {
        List<Object[]> trend = geulgwiTagRepository.getTrend().subList(0, 6);
        List<TagDTO.Response> tagDTOS = new ArrayList<>();

        for (Object[] row : trend) {
            Tag tag = (Tag) row[0]; //Tag
            Integer tagCount = (Integer) row[1]; //태그 개수

            tagDTOS.add(
                    TagDTO.Response.builder()
                            .tagSeq(tag.getTagSeq())
                            .backColor(tag.getBackColor())
                            .fontColor(tag.getFontColor())
                            .value(tag.getValue())
                            .count(tagCount)
                            .build()
            );
        }

        return tagDTOS;
    }
}
