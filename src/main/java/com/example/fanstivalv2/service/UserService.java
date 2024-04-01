package com.example.fanstivalv2.service;

import com.example.fanstivalv2.domain.User;
import com.example.fanstivalv2.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.fanstivalv2.repository.UserRepository;
import com.example.fanstivalv2.web.UserSignUpDto;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception {

        if(userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일 입니다");
        }

        if(userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .nickname(userSignUpDto.getNickname())
                .age(userSignUpDto.getAge())
                .profileImage(userSignUpDto.getProfileImage())
                .role(Role.FAN)//처음 생성시에는 Fan으로 등록
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }
}
