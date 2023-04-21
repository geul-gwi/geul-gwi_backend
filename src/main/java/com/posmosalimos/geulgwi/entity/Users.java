package com.posmosalimos.geulgwi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Users {
    @Id @GeneratedValue
    Long userNumber;
    String userId;
    String userPassword;
    String userName;
    String userAge;
    String userGender;
    String userAddress;
    @ColumnDefault("USER")
    String role;

}
