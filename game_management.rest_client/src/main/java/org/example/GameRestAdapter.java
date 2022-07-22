package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
public class GameRestAdapter implements GameService
{

    private RestTemplate restTemplate;
    final String localhost = "http://localhost:8080/game/";

    @Autowired
    public GameRestAdapter(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }

   /*
    @GetMapping(value = "/all")
    public List<Game> getGameList() {*/


    public Game createGame(User requester, User receiver){
        String reqName = requester.getUserName();
        String recName = receiver.getUserName();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "create/" + reqName + "/" + recName;
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Game.class).getBody();
    }

    public void calculatePoints(Game game, User user, int points){
       String gameId = String.valueOf(game.getGameId());
       String userName = user.getUserName();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "calculatePoints/" + gameId + "/" + userName + "/" + points;
        String result = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody();
    }

    @Override
    public void calculatePoints(Long gameId, String userName, int points) {

    }

    public List<Game> getGamesFromCurrentUser(String userName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "gamesOfUser" + "/" + userName;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class).getBody();
    }

    public List<Question> giveQuestions(Game game, User currentUser, VocabList vocabList){
        String userName = currentUser.getUserName();
        String gameId = String.valueOf(game.getGameId());
        String vocablistId = String.valueOf(vocabList.getVocabListId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "getQuestions" + "/" + gameId + "/" + userName + "/" + vocablistId;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class).getBody();
    }

    @Override
    public Game getGamebyId(Long gameId) {
        return null;
    }

    @Override
    public List<Game> getALlGames() {
        return null;
    }

}
