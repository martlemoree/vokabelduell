package org.example;

import de.htwberlin.kba.game_management.export.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GameRestAdapter
{
    //Todo was müssen hier für methoden rein?

    private RestTemplate restTemplate;

    @Autowired
    public GameRestAdapter(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }

    public String createGame(Game game){

        final String URL = "http://localhost:8080/game/addGame/";

        //HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Game> entity = new HttpEntity<>(game);

       return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
    }
    /*
    public void calculatePoints(String userName, int points){
        RestTemplate restTemplate = new RestTemplate();

        final String URL = "http://localhost:8080/game/calculatePoints/" + userName + "/"
                + points;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

    }        */

   // @GetMapping("/gamesOfUser/{name}")
    //public List<Game> getGamesFromCurrentUser(@PathVariable("name") String name)
    public List<Game> getGamesFromCurrentUser(String name){
        final String URL = "http://localhost:8080/game/gamesOfUser/" + name;
        HttpEntity<String> entity = new HttpEntity<>(name); 
        return restTemplate.exchange(URL, HttpMethod.GET, entity, List.class).getBody();
    }

}
