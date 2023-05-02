package com.posmosalimos.geulgwi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}
