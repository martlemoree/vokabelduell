package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class RoundRestAdapter {
    private RestTemplate restTemplate;

    @Autowired
    public RoundRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    final String localhost = "http://localhost:8080/round/";
    //test

    public Round createRound(Game game){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String gameId = String.valueOf(game.getGameId());
        final String URL = localhost + "create/" + gameId;
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Round.class).getBody();
    }

    public void changeLastPlayer(Game game, User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String gameId = String.valueOf(game.getGameId());
        String userName = user.getUserName();
        final String URL = localhost + "changeLastPlayer/" + gameId + "/" + userName;
        String result = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody();
    }


}
