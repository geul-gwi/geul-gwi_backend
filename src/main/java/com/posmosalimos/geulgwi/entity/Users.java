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
    Long userNumber;
    String userId;
    String userPassword;
    String userName;
    int userAge;
    String userGender;
    String userNickname;
    String tag1;
    String tag2;
    String tag3;
    String userProfile;
//    @ColumnDefault("USER")
    String role;


}
