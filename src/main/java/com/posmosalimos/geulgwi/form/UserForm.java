package com.posmosalimos.geulgwi.form;

import com.posmosalimos.geulgwi.entity.Role;
import jakarta.persistence.Table;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserForm {

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
