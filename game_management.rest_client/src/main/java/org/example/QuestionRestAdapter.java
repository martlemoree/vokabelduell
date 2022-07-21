package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.VocabList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class QuestionRestAdapter {

    private RestTemplate restTemplate;
    final String localhost = "http://localhost:8080/round/";
    @Autowired
    public QuestionRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

    public Translation setAnswerOptions(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);

        final String URL = localhost + "setAnswerOptions";
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity, Translation.class).getBody();
    }

    public List<Translation> getAllAnswers(List<Question> questions, int i){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(questions,headers);

        final String URL = localhost + "getAllAnswers" + "/" + i;
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

    public List<String> giveAnswerOptionsRandom(List<Question> questions, int i){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(questions,headers);

        final String URL = localhost + "giveAnswerOptionsRandom" + "/" + i;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity,new ParameterizedTypeReference<List<String>>() {}).getBody();


    }

    public boolean answeredQuestion(String answer, List<Question> questions, int i){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(questions,headers);

        final String URL = localhost + "answeredQuestion" + "/" + i;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity,boolean.class).getBody();
    }

    public String giveVocabStringRandom(List<Question> questions, int i){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(questions,headers);

        final String URL = localhost + "giveVocabStringRandom" + "/" + i;
        return restTemplate.exchange(URL, HttpMethod.GET, requestEntity,String.class).getBody();
    }

}
