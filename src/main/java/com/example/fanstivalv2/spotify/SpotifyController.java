package com.example.fanstivalv2.spotify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;
    private final CreateSpotifyToken createSpotifyToken; // 추가된 부분

    @Autowired
    public SpotifyController(SpotifyService spotifyService, CreateSpotifyToken createSpotifyToken) { // 생성자 수정
        this.spotifyService = spotifyService;
        this.createSpotifyToken = createSpotifyToken; // 추가된 부분
    }

    @PostMapping("/saveArtist")
    public void saveArtist(@RequestParam String artistName) {
        String accessToken = createSpotifyToken.accessToken(); // 수정된 부분
        // 액세스 토큰 생성
        log.info(accessToken);
        if (!"error".equals(accessToken)) {
            spotifyService.saveArtistFromSpotify(accessToken, artistName);
        } else {
            // 액세스 토큰 생성 실패 처리
            log.error("Spotify 액세스 토큰 생성에 실패했습니다."); // System.out.println 대신 log.error 사용
        }
    }
}
