package jwt.jwtservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {

    @org.springframework.beans.factory.annotation.Value("${jwt.secretKey}")
    private String secretKey;

    @org.springframework.beans.factory.annotation.Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @org.springframework.beans.factory.annotation.Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @org.springframework.beans.factory.annotation.Value("${jwt.access.header}")
    private String accessHeader;

    @org.springframework.beans.factory.annotation.Value("${jwt.refresh.header}")
    private String refreshHeader;

    //JWT's Subject와 Claim = email
    //Jwt 헤더에 담기는 값: Authorizaation(key) = Bearer{toekn} (value)형식

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String EMAIL_CLAIM = "email";
    private static final String BEARER = "Bearer";

    private final UserRepository userRepository;

    public String createAccessToken(String email){
        Date now = new Date();
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))//토큰 만료시간 설정
                .withClaim(EMAIL_CLAIM, email)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public String createRefreshToken(){
        Date now = new Date();
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .sign(Algorithm.HMAC512(secretKey));
    }

    //AccessToken 헤더에 실어서 보내기
    public void sendAccessToken(HttpServletResponse response, String accessToken){
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, accessToken);
        log.info("재발급된 Access Token : {}", accessToken);
    }

    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken){
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
        setRefreshTokenHeader(response, refreshToken);
        log.info("Access Token, Refresh Token 헤더 설정 완료");
    }

    //헤더에서 RefreshToken 추출
    //토큰 형식: Bearer XX에서 XX만 가져오기 위해
    //헤더를 가져온 후 Bearer를 삭제
    public Optional<String> extractRefreshToken(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    //헤더에서 AccessToken 추출
    //토큰 형식: Bearer XX에서 XX만 가져오기 위해
    //헤더를 가져온 후 Bearer를 삭제

    public Optional<String> extractAccessToken(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    //Access Token에서 Email 추출, 추출 전에 JWT.require()로 검증기 생성
    //verify로 AccessToken 검중 후 유효하지 않으면 getClaim()으로 이메일 추출 유효하지 않다면
    //빈 Optional 객체 반환
    public Optional<String> extractEmail(String accessToken){
        try{
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(accessToken)
                    .getClaim(EMAIL_CLAIM)
                    .asString());

        }catch(Exception e){
            log.error("액세스 토큰이 유효하지 않음.");
            return Optional.empty();
        }
    }

    public void setAccessTokenHeader(HttpServletResponse response, String accessToken){
        response.setHeader(accessHeader, accessToken);
    }

    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken){
        response.setHeader(refreshHeader, refreshToken);
    }

    //refreshToken DB 업데이트
    public void updateRefreshToken(String email, String refreshToken){
        userRepository.findByEmail(email)
                .ifPresentOrElse(
                        user -> user.updateRefreshToken(refreshToken),
                        () -> new Exception("일치하는 회원이 없습니다.")
                );
    }

    public boolean isTokenVaild(String token){
        try{
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
            return true;
        }catch (Exception e){
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            return false;
        }
    }
}
