package com.posmosalimos.geulgwi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(
        name = "USERS_SEQ_GENERATOR",
        sequenceName = "Users_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, //초기 값
        allocationSize = 1 //미리 할당 받을 시퀸스 수
)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "USERS_SEQ_GENERATOR")
    private Long userNumber;
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
    private Role role;


}
