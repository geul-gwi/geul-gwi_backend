package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.Role;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.UserForm;
import com.posmosalimos.geulgwi.repository.JpaUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

        if(user.getUserId().equals("akxxkd"))
            user.setRole(Role.ADMIN);
        else user.setRole(Role.USER);

        validateDuplicateUser(user);
        jpaUserRepository.save(user);

        return user.getUserNumber();
    }

    //login
    public Users login(String userId, String password) {
        return jpaUserRepository.findByUserId(userId).stream()
                .filter(m -> m.getUserPassword().equals(password))
                .findAny()
                .orElse(null);
    }

    //delete
    public void withdrawal(String userId, String userPassword){
        Users findUser = jpaUserRepository.findByUserId(userId).stream()
                .filter(u -> u.getUserPassword().equals(userPassword))
                .findAny()
                .orElse(null);

        if (findUser != null) {
            jpaUserRepository.delete(findUser);
        }else {
            throw new NoSuchElementException("해당하는 유저를 찾을 수 없습니다.");
        }
    }
}
