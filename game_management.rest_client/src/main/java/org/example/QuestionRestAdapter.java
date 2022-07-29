package org.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.VocabList;


@Service
public class QuestionRestAdapter implements QuestionService {

    private RestTemplate restTemplate;
    final String localhost = "http://localhost:8080/round/";
    @Autowired
    public QuestionRestAdapter(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }

    public Question createQuestion(Round round, VocabList vocabList){
        Long roundId = round.getRoundId();
        Long vocablistId = vocabList.getVocabListId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "create/" + roundId + "/" + vocablistId;
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Question.class).getBody();
    }

    public List<Translation> getAllAnswers(Question question){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "getAllAnswers" + "/" + question.getQuestionId();
        return  restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class).getBody();
    }

    public List<Question> createQuestions(Game game, VocabList chosenVocabList, Round round){
        String gameId = String.valueOf(game.getGameId());
        String roundId = String.valueOf(round.getRoundId());
        String vocablistId = String.valueOf(chosenVocabList.getVocabListId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);

        final String URL = localhost + "createQuestions/" + gameId + "/" + vocablistId + "/" + roundId;
        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, List.class).getBody();
    }

    public List<String> giveAnswerOptionsRandom(Question question){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "giveAnswerOptionsRandom" + "/" + question.getQuestionId();
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity,new ParameterizedTypeReference<List<String>>() {}).getBody();


    }

    public boolean answeredQuestion(String answer, Question question){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "answeredQuestion" + "/" + answer +  "/"  + question.getQuestionId();
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity,boolean.class).getBody();
    }

    public String giveVocabStringRandom(Question question){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        final String URL = localhost + "giveVocabStringRandom" + "/" + question.getQuestionId();
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity,String.class).getBody();
    }


    @Override
    public List<Question> getAllQuestions() {
        return null;
    }

    @Override
    public Question getQuestionById(Long Id) {
        return null;
    }

}
