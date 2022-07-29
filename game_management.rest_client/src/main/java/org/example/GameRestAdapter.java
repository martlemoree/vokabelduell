package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
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

    public List<List<String>> giveQuestions(Long gameId, String userName, Long vocablistId) {
        // Wo wird der vocabList-Parameter befüllt bei einer laufenden Runde?

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>("parameters",headers);

        final String URL = localhost + "getQuestions" + "/" + gameId + "/" + userName + "/" + vocablistId;


        ResponseEntity<Long> result = restTemplate.exchange(URL, HttpMethod.GET, requestEntity, Long.class);


        List<String> question1 = new ArrayList<>();
        question1.add(result.getHeaders().get("question1").toString());
        question1.add(result.getHeaders().get("question1answer1").toString());
        question1.add(result.getHeaders().get("question1answer2").toString());
        question1.add(result.getHeaders().get("question1answer3").toString());
        question1.add(result.getHeaders().get("question1answer4").toString());
        question1.add(result.getHeaders().get("questionId1").toString());

        List<String> question2 = new ArrayList<>();
        question2.add(result.getHeaders().get("question2").toString());
        question2.add(result.getHeaders().get("question2answer1").toString());
        question2.add(result.getHeaders().get("question2answer2").toString());
        question2.add(result.getHeaders().get("question2answer3").toString());
        question2.add(result.getHeaders().get("question2answer4").toString());
        question1.add(result.getHeaders().get("questionId2").toString());

        List<String> question3 = new ArrayList<>();
        question3.add(result.getHeaders().get("question3").toString());
        question3.add(result.getHeaders().get("question3answer1").toString());
        question3.add(result.getHeaders().get("question3answer2").toString());
        question3.add(result.getHeaders().get("question3answer3").toString());
        question3.add(result.getHeaders().get("question3answer4").toString());
        question1.add(result.getHeaders().get("questionId3").toString());


        List<List<String>> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        return questions;
    }

    public String giveVocabStringRandom(Long questionId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "giveVocabStringRandom" + "/" + questionId;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity,String.class).getBody();
    }

    public List<String> giveAnswerOptionsRandom(Long questionId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "giveAnswerOptionsRandom" + "/" + questionId;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity,new ParameterizedTypeReference<List<String>>() {}).getBody();


    }
    @Override
    public Game getGamebyId(Long gameId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + gameId;
        Game game = restTemplate.exchange(URL, HttpMethod.GET, requestEntity, Game.class).getBody(); // Hier passiert der Fehler, nicht im return
        String lol = "lol";
        return game;
    }

    @Override
    public List<Game> getALlGames() {
        return null;
    }

}
