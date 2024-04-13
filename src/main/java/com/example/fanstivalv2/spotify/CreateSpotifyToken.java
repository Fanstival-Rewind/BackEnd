package com.example.fanstivalv2.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import jakarta.annotation.PostConstruct;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class CreateSpotifyToken {

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    private SpotifyApi spotifyApi;

    @PostConstruct // 이 어노테이션은 해당 메소드가 빈의 초기화가 완료된 후 실행되도록 합니다.
    private void initializeSpotifyApi() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }

    public String accessToken() {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            return spotifyApi.getAccessToken();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error " + e.getMessage());
            return "error";
        }
    }
}

