package com.example.fanstivalv2.ouath2.handler;

import com.example.fanstivalv2.domain.enums.Role;
import com.example.fanstivalv2.jwt.jwtservice.JwtService;
import com.example.fanstivalv2.ouath2.CustomOAuth2User;
import com.example.fanstivalv2.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)throws IOException, ServletException {
        log.info("OAuth2 Login Success");
        try{
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            if(oAuth2User.getRole() == Role.GUEST){
                String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
                response.addHeader(jwtService.getAccessHeader(), "Bearer" + accessToken);
                response.sendRedirect("oauth2/sign-up");

                jwtService.sendAccessAndRefreshToken(response, accessToken, null);
            }else{
                loginSuccess(response, oAuth2User);
            }
        }catch(Exception e){
            throw e;
        }
    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException{
        String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
        String refreshToken = jwtService.createRefreshToken();
        response.addHeader(jwtService.getAccessHeader(), "Bearer" + accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer" + refreshToken);

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
    }
}
