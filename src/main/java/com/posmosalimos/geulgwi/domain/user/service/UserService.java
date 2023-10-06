package com.posmosalimos.geulgwi.domain.user.service;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.repository.UserRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import com.posmosalimos.geulgwi.global.error.exception.EntityNotFoundException;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public User findUserByRefreshToken(String refreshToken){
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
        User findUser = userRepository.findByUserSeq(userSeq).orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        if (findUser.getPassword().equals(userPassword))
            userRepository.delete(findUser);
        else throw new BusinessException(ErrorCode.INVALID_PASSWORD);
    }

    //delete - admin
    @Transactional
    public void delete(Long userSeq) {
        User findUser = userRepository.findByUserSeq(userSeq).orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));
        userRepository.delete(findUser);
    }

    public UserInfoDto findUserInfo(Long userSeq){
        Optional<User> findUser = userRepository.findByUserSeq(userSeq);
        if (findUser.isEmpty())
            throw new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS);

        return UserInfoDto.builder()
                .userSeq(userSeq)
                .userId(findUser.get().getUserId())
                .nickname(findUser.get().getNickname())
                .role(findUser.get().getRole())
                .userPassword(findUser.get().getPassword())
                .profile(findUser.get().getUserProfile())
                .build();
    }

    public User findBySeq(Long userSeq) {
        return userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.MEMBER_NOT_EXISTS));
    }

    public Boolean findByUserId(String userId) {

        if (userRepository.findByUserId(userId).isPresent())
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);

        return true;
    }

    public Boolean findByNickname(String nickname) {

        if (userRepository.findByUserNickname(nickname).isPresent())
            throw new BusinessException(ErrorCode.DUPLICATED_NICKNAME);

        return true;
    }

}
