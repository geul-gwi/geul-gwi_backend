package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.Role;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.User.UpdateForm;
import com.posmosalimos.geulgwi.form.User.UserForm;
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

    //validateDuplicateUserId
    public String validateDuplicateUserId(String id) {
        Optional<Users> findUserById = jpaUserRepository.findByUserId(id);

        if (!findUserById.isEmpty()) {
//            throw new IllegalStateException("중복된 아이디입니다.");
            return "fail";
        }

        return "success";
    }

    //validateDuplicateUserNickname
    public String validateDuplicateUserNickname(String nickname) {
        Optional<Users> findUserByNickname = jpaUserRepository.findByUserNickname(nickname);

        if (!findUserByNickname.isEmpty()) {
//            throw new IllegalStateException("중복된 닉네임입니다");
            return "fail";
        }

        return "success";
    }

    public Users findUserByIdAndPassword(String userId, String userPassword){
        return jpaUserRepository.findByUserId(userId).stream()
                .filter(u -> u.getUserPassword().equals(userPassword))
                .findAny()
                .orElse(null);
    }

    //join
    @Transactional
    public void join(@Valid UserForm form) {

        Users user = Users.builder()
                .form(form)
                .build();

        if(form.getUserId().equals("akxxkd"))
            user.setRole(Role.ADMIN);
        else user.setRole(Role.USER);

        jpaUserRepository.save(user);
    }

    //login
    public Users login(String userId, String password) {
        return findUserByIdAndPassword(userId, password);
    }

    //delete
    @Transactional
    public void withdrawalUser(String userId, String userPassword) {
        Users findUser = findUserByIdAndPassword(userId, userPassword);

        if (findUser != null)
            jpaUserRepository.delete(findUser);
        else
            throw new NoSuchElementException("해당하는 유저를 찾을 수 없습니다.");

    }

    //update
    @Transactional
    public Long updateUser(Users findUser, UpdateForm form) {
        if (findUser != null && form.getUserPassword_current().equals(findUser.getUserPassword())) {
            findUser.update(form);

            jpaUserRepository.save(findUser);
        }
        return findUser.getUserSeq();
    }

    //find password
    public String findPassword(String id, String name) {
        String password = jpaUserRepository.findPasswordByIdAndName(id, name);
        if (password == null)
            return "";
        else
            return password;
    }
}
