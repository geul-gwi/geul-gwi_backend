package com.posmosalimos.geulgwi.form;

import com.posmosalimos.geulgwi.entity.Role;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserForm {
    @NotEmpty(message = "값을 입력해 주세요")
    private String userId;
    @NotEmpty(message = "값을 입력해 주세요")
    private String userPassword;
    @NotEmpty(message = "값을 입력해 주세요")
    private String userName;
    @NotNull(message = "값을 입력해 주세요")
    private int userAge;
    @NotEmpty(message = "값을 입력해 주세요")
    private String userGender;
    @NotEmpty(message = "값을 입력해 주세요")
    private String userNickname;
    private String tag1;
    private String tag2;
    private String tag3;
    private String userProfile;
    private Role role;

}
