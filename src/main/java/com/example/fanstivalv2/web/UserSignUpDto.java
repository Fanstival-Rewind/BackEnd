package com.example.fanstivalv2.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDto {

    private String email;
    private String password;
    private String nickname;
    private int age;
    private String profileImage;
    private String city;
}
