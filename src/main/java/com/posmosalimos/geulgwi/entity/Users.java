package com.posmosalimos.geulgwi.entity;

import com.posmosalimos.geulgwi.form.User.UserForm;
import com.posmosalimos.geulgwi.form.User.dto.UpdateForm;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;
    private String userId;
    private String userPassword;
    private String userName;
    private int userAge;
    private String userGender;
    private String userNickname;
    private String tag1;
    private String tag2;
    private String tag3;
    private String userProfile;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    public Users(UserForm form){

        Assert.hasText(form.getUserId(), "userId must not be empty");
        Assert.hasText(form.getUserPassword(), "userPassword must not be empty");
        Assert.hasText(form.getUserName(), "userName must not be empty");

        this.userId = form.getUserId();
        this.userPassword = form.getUserPassword();
        this.userName = form.getUserName();
        this.userAge = form.getUserAge();
        this.userGender = form.getUserGender();
        this.userNickname = form.getUserNickname();
        this.tag1 = form.getTag1();
        this.tag2 = form.getTag2();
        this.tag3 = form.getTag3();
        this.userProfile = form.getUserProfile();
    }

    public void update(UpdateForm form){
        this.userPassword = form.getUserPassword_new();
        this.userName = form.getUserName();
        this.userNickname = form.getUserNickname();
        this.tag1 = form.getTag1();
        this.tag2 = form.getTag2();
        this.tag3 = form.getTag3();
        this.userProfile = form.getUserProfile();
    }
}
