package com.posmosalimos.geulgwi.domain.user.service;

import com.posmosalimos.geulgwi.api.tag.search.dto.TagDTO;
import com.posmosalimos.geulgwi.api.user.search.dto.UserListDTO;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.repository.UserRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import com.posmosalimos.geulgwi.global.error.exception.EntityNotFoundException;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FileService fileService;

    //join
    @Transactional
    public User join(User user) {

        return userRepository.save(user);
    }

    //login
    public User findByIdAndPassword(String userId, String userPassword){
        return userRepository.findByUserId(userId).stream()
                .filter(u -> u.getPassword().equals(userPassword))
                .findAny()
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public User findUserByRefreshToken(String refreshToken) {
        User findUser = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

        LocalDateTime tokenExpirationTime = findUser.getTokenExpirationTime();
        if (tokenExpirationTime.isBefore(LocalDateTime.now()))
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED);

        return findUser;
    }

    //delete - user
    @Transactional
    public void delete(Long userSeq, String userPassword) {
        User findUser = userRepository.findByUserSeq(userSeq).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (findUser.getPassword().equals(userPassword))
            userRepository.delete(findUser);
        else throw new BusinessException(ErrorCode.INVALID_PASSWORD);
    }

    //delete - admin
    @Transactional
    public void delete(Long userSeq) {
        User findUser = userRepository.findByUserSeq(userSeq).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(findUser);
    }

    //유저정보 - 단건
    public UserInfoDTO findUserInfo(Long userSeq) {
        User findUser = userRepository.findByUserSeq(userSeq).orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        List<TagDTO> tags = findUser.getUserTags().stream()
                .map(UserTag::getTag)
                .map(TagDTO::from)
                .collect(Collectors.toList());

        String profile = fileService.findByUser(findUser);

        return UserInfoDTO.builder()
                .userSeq(userSeq)
                .userId(findUser.getUserId())
                .nickname(findUser.getNickname())
                .password(findUser.getPassword())
                .email(findUser.getEmail())
                .comment(findUser.getEmail())
                .role(findUser.getRole())
                .profile(profile)
                .comment(findUser.getComment())
                .tags(tags)
                .build();
    }

    //유저정보 - 복수건
    public List<UserListDTO> findUserInfos() {
        List<User> users = userRepository.findAll();
        List<UserListDTO> userListDtos = new ArrayList<>();

        for (User u : users) {
            String profile = Optional.ofNullable(u.getUploadFile())
                    .map(uploadFile -> uploadFile.getStore())
                    .orElse(null);

            userListDtos.add(
                    UserListDTO.builder()
                            .userSeq(u.getUserSeq())
                            .userId(u.getUserId())
                            .nickname(u.getNickname())
                            .profile(profile)
                            .build()
            );
        }

        return userListDtos;
    }

    public User findBySeq(Long userSeq) {
        return userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.USER_NOT_FOUND));
    }

    public Boolean findByUserId(String userId) {

        if (userRepository.findByUserId(userId).isPresent())
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_USER);

        return true;
    }

    public Boolean findByNickname(String nickname) {

        if (userRepository.findByUserNickname(nickname).isPresent())
            throw new BusinessException(ErrorCode.DUPLICATED_NICKNAME);

        return true;
    }

    public String findByEmail(String email) {
        User findUser = userRepository.findByEmail(email)
                .orElse(null);

        return findUser == null ? "true" : findUser.getUserId();
    }

    public List<String> findByIdAndEmail(String userId, String email) {

        User findUser = userRepository.findByIdAndEmail(userId, email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        List<String> info = new ArrayList<>();
        info.add(findUser.getNickname());
        info.add(findUser.getPassword());

        return info;
    }



}
