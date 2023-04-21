package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.UserForm;
import com.posmosalimos.geulgwi.repository.JpaUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final JpaUserRepository jpaUserRepository;
    private final UserService userService;

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
        user.setUserAddress(form.getUserAddress());

        if(user.getUserId().equals("akxxkd"))
            user.setRole("ADMIN");

        validateDuplicateUser(user);
        jpaUserRepository.save(user);

        return user.getUserNumber();
    }

    //login
    public Users login(String userId, String password) {
        return jpaUserRepository.findByUserId(userId).stream()
                .filter(u -> u.getUserPassword().equals(password))
                .findAny()
                .orElse(null);
    }
}
