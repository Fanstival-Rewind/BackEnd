package com.example.fanstivalv2.repository;

import com.example.fanstivalv2.domain.User;
import com.example.fanstivalv2.domain.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findBySocialTypeAAndSocialId(SocialType socialType);
    //OAuth2로그인 구현 시 사용
}
