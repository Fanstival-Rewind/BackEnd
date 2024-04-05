package com.example.fanstivalv2.ouath2.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    public abstract String getId();
    public abstract String getNickname();
    public abstract String getImageUrl();
}
