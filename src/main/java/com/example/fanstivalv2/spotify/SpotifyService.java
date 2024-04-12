package com.example.fanstivalv2.spotify;

import com.example.fanstivalv2.domain.Artist;
import com.example.fanstivalv2.repository.ArtistRepository;
import com.example.fanstivalv2.repository.SongRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Getter
public class SpotifyService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public String artistSearch(String accessToken, String q){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer" + accessToken);
        headers.add("Content-type", "application/json");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.spotify.com/v1/search?type=artist&q="+q, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = (HttpStatus) responseEntity.getStatusCode();
        int status = httpStatus.value();

        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    @Transactional
    public void saveArtistFromSpotify(String accessToken, String q) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        String url = "https://api.spotify.com/v1/search?type=artist&q=" + q + "&market=KR";

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.getBody();

        JSONObject jsonResponse = new JSONObject(response); // 수정된 부분
        JSONArray artists = jsonResponse.getJSONObject("artists").getJSONArray("items");

        for (int i = 0; i < artists.length(); i++) {
            JSONObject artistJson = artists.getJSONObject(i);
            Artist artist = Artist.builder()
                    .name(artistJson.getString("name"))
                    .nationality("KR") // 예시에서는 모든 아티스트를 'KR'로 설정
                    .popularity(artistJson.getInt("popularity"))
                    .build();
            artistRepository.save(artist);

        }
    }

}
