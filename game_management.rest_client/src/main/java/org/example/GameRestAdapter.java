package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.springframework.web.client.RestTemplate;

@Service
public class GameRestAdapter implements GameService
{

    private RestTemplate restTemplate;
    final String localhost = "http://localhost:8080/game/";

    @Autowired
    public GameRestAdapter(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }


    public Long createGame(User requester, User receiver){
        String reqName = requester.getUserName();
        String recName = receiver.getUserName();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>("parameters",headers);

        final String URL = localhost + "create/" + reqName + "/" + recName;
//        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Game.class).getBody();

        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, requestEntity,String.class);
        Long gameId = Long.valueOf(result.getBody());
        return gameId;
    }

//    public Long createGame(User requester, User receiver){
//        String reqName = requester.getUserName();
//        String recName = receiver.getUserName();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//
//
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
////Add the Jackson Message converter
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//
//// Note: here we are making this converter to process any kind of response,
//// not only application/*json, which is the default behaviour
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);
//
//
//
//        final String URL = localhost + "create/" + reqName + "/" + recName;
//        Game game = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Game.class).getBody();
//        return game.getGameId();
//    }

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
        String vocablistId = String.valueOf(vocabList.getVocabListId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "getQuestions" + "/" + game.getGameId() + "/" + userName + "/" + vocablistId;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class).getBody();
    }
    @Override
    public Game getGamebyId(Long gameId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + gameId;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, Game.class).getBody();
    }

    @Override
    public List<Game> getALlGames() {
        return null;
    }

}
