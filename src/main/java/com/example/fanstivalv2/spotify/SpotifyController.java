package com.example.fanstivalv2.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @PostMapping("/saveArtist")
    public void saveArtist(@RequestParam String artistName) {
        String accessToken = CreateSpotifyToken.accessToken(); // 액세스 토큰 생성
        if (!"error".equals(accessToken)) {
            spotifyService.saveArtistFromSpotify(accessToken, artistName);
        } else {
            // 액세스 토큰 생성 실패 처리
            System.out.println("Spotify 액세스 토큰 생성에 실패했습니다.");
        }
    }
}
