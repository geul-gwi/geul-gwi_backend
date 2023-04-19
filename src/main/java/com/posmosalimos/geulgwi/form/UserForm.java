package com.posmosalimos.geulgwi.form;

import jakarta.persistence.Table;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserForm {

    private String userId;
    private String userPassword;
    private String userName;
    private String userAge;
    private String userGender;
    private String userAddress;
    private String role;
}
