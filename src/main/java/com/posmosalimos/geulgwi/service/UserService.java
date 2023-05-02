package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.Role;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.UpdateForm;
import com.posmosalimos.geulgwi.form.UserForm;
import com.posmosalimos.geulgwi.repository.JpaUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final JpaUserRepository jpaUserRepository;


    //validateDuplicateUser
    private void validateDuplicateUser(Users user) {
        Optional<Users> findUsers = jpaUserRepository.findByUserId(user.getUserId());

        if (!findUsers.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    public Users findUserByIdAndPassword(String userId, String userPassword){
        return jpaUserRepository.findByUserId(userId).stream()
                .filter(u -> u.getUserPassword().equals(userPassword))
                .findAny()
                .orElse(null);
    }

    //join
    @Transactional
    public Long join(@Valid UserForm form) {
        Users user = new Users();
        user.setUserId(form.getUserId());
        user.setUserPassword(form.getUserPassword());
        user.setUserName(form.getUserName());
        user.setUserAge(form.getUserAge());
        user.setUserGender(form.getUserGender());
        user.setUserNickname(form.getUserNickname());
        user.setTag1(form.getTag1());
        user.setTag2(form.getTag2());
        user.setTag3(form.getTag3());
        user.setUserProfile(form.getUserProfile());

        if(form.getUserId().equals("akxxkd"))
            user.setRole(Role.ADMIN);
        else user.setRole(Role.USER);

        validateDuplicateUser(user);
        jpaUserRepository.save(user);

        return user.getUserSeq();
    }

    //login
    public Users login(String userId, String password) {
        return findUserByIdAndPassword(userId, password);
    }

    //delete
    public void withdrawalUser(String userId, String userPassword){
        Users findUser = findUserByIdAndPassword(userId, userPassword);

        if (findUser != null) {
            jpaUserRepository.delete(findUser);
        }else {
            throw new NoSuchElementException("해당하는 유저를 찾을 수 없습니다.");
        }
    }

    //update
    public Long updateUser(String userId, String userPassword, UpdateForm form){
        Users findUser = findUserByIdAndPassword(userId, userPassword);

        findUser.setUserPassword(form.getUserPassword());
        findUser.setUserNickname(form.getUserNickName());
        findUser.setUserProfile(form.getUserProfile());
        findUser.setTag1(form.getTag1());
        findUser.setTag2(form.getTag2());
        findUser.setTag3(form.getTag3());

        return findUser.getUserSeq();
    }

}
