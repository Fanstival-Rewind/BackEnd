package com.example.fanstivalv2.domain;

import com.example.fanstivalv2.domain.common.BaseEntity;
import com.example.fanstivalv2.domain.enums.Grade;
import com.example.fanstivalv2.domain.enums.Role;
import com.example.fanstivalv2.domain.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String nickname;

    private String password; // 비밀번호

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // Enum type으로 kakao,naver, google 사용

    private String socialId;

    private String profileImage;

    private String city;

    private int age;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("1")
    private int status; // 0: 비활성화, 1: 활성

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role; // enum type으로 가수, 팬, 둘 다로 생성

    private Grade grade; // enum type으로 등급 정하기

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String refreshToken; // 리프레쉬 토큰

    @Builder.Default
    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follower> followers = new ArrayList<>(); // 특정 사용자를 팔로우하는 사람들의 목록

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}
