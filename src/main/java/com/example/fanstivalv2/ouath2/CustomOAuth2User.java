package com.example.fanstivalv2.ouath2;

import com.example.fanstivalv2.domain.enums.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private String email;
    private Role role;

    //DefaultOAuth2User 상속후, email과 role  필드를 추가로 가진다.

    /*
     * @param authorities      유저의 authorities
     * @param attributes       유저에 대한 attributes
     * @param nameAttributeKey getAttributes()에서 사용자의 name에 접근하기 위해 사용되는 키
     */
    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            String email, Role role){
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.role = role;
    }
}
