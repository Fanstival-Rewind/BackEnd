package com.example.fanstivalv2.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class CreateSpotifyToken {


    private static final String CLIENT_ID = "${spotify.client.id}";

    private static final String CLIENT_SECRET = "${spotify.client.secret}";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(CLIENT_ID).setClientSecret(CLIENT_SECRET).build();

    public static String accessToken(){
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        try{
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
                return spotifyApi.getAccessToken();
        }catch (IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error " + e.getMessage());
            return "error";
        }
    }


}
