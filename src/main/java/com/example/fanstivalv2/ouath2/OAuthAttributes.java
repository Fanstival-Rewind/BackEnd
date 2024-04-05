package com.example.fanstivalv2.ouath2;

//OAuth DTO클래스

import com.example.fanstivalv2.domain.User;
import com.example.fanstivalv2.domain.enums.Role;
import com.example.fanstivalv2.domain.enums.SocialType;
import com.example.fanstivalv2.ouath2.userinfo.GoogleOAuth2UserInfo;
import com.example.fanstivalv2.ouath2.userinfo.KakaoOAuth2UserInfo;
import com.example.fanstivalv2.ouath2.userinfo.NaverOAuth2UserInfo;
import com.example.fanstivalv2.ouath2.userinfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey;//Oauth2 로그인 시 키가 되는 필드 값, PK의 의미
    private OAuth2UserInfo oAuth2UserInfo;//소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등)

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo){
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    /*
     * SocialType에 맞는 메소드 호출하여 OAuthAttributes 객체 반환
     * 파라미터 : userNameAttributeName -> OAuth2 로그인 시 키(PK)가 되는 값 / attributes : OAuth 서비스의 유저 정보들
     * 소셜별 of 메소드(ofGoogle, ofKaKao, ofNaver)들은 각각 소셜 로그인 API에서 제공하는
     * 회원의 식별값(id), attributes, nameAttributeKey를 저장 후 build
     */
    public static OAuthAttributes of(SocialType socialType, String userNameAttributeName, Map<String, Object> attributes){
        if (socialType == SocialType.NAVER){
            return ofNaver(userNameAttributeName, attributes);
        }
        if (socialType == SocialType.KAKAO){
            return ofKakao(userNameAttributeName, attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }

    public User toEntity(SocialType socialType, OAuth2UserInfo oAuth2UserInfo){
        return User.builder()
                .socialType(socialType)
                .socialId(oAuth2UserInfo.getId())
                .email(UUID.randomUUID() + "@socialUser.com")
                .nickname(oAuth2UserInfo.getNickname())
                .profileImage(oAuth2UserInfo.getImageUrl())
                .role(Role.GUEST)
                .build();
    }
}
