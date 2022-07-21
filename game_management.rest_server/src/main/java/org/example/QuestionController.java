package org.example;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    public QuestionService questionService;
    public RoundService roundService;
    public VocabListService vocabListService;
    public GameService gameService;

    @Autowired
    public QuestionController(QuestionService questionService, RoundService roundService, VocabListService vocabListService, GameService gameService) {
        this.questionService = questionService;
        this.roundService = roundService;
        this.vocabListService = vocabListService;
        this.gameService = gameService;
    }




    /*
    @GetMapping("/getAllAnswers/{questionId}/{}")
    public List<Translation> getAllAnswers(@RequestBody Question question){
        return service.getAllAnswers(question);
    }*/


    @PostMapping(value = "/create/{roundId}/{vocablistId}")
    public ResponseEntity<Void> createQuestion(@PathVariable("roundId") Long roundId, @PathVariable("vocablistId") Long vocablistId) throws URISyntaxException {
        Round round = roundService.getRoundById(roundId);
        VocabList vocabList = vocabListService.getVocabListById(vocablistId);

        Question newQuestion = questionService.createQuestion(round, vocabList);
        URI uri = new URI("/question/" + newQuestion.getQuestionId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/all")
    public List<Question> getQuestionList() {
        List<Question> questions = questionService.getAllQuestions();
        return questions;
    }

    //TODO testen
    @GetMapping(value = "/setAnswerOption")
    public Translation setAnswerOptions() {
        return questionService.setAnswerOptions();
    }

    //TODO testen
    @GetMapping(value = "/getAllAnswers/{i}")
    public List<Translation> getAllAnswers(@RequestBody List<Question> questions, @PathVariable("i") int i) {
        return questionService.getAllAnswers(questions, i);
    }

    //TODO testen
    @PostMapping(value = "/createQuestions/{gameId}/{vocablistId}/{roundId}")
    public void createQuestions(@PathVariable("gameId") Long gameId, @PathVariable("vocablistId") Long vocablistId, @PathVariable("roundId") Long roundId) throws URISyntaxException {
        Round round = roundService.getRoundById(roundId);
        VocabList vocabList = vocabListService.getVocabListById(vocablistId);
        Game game = gameService.getGamebyId(gameId);

        List<Question> questions = questionService.createQuestions(game, vocabList, round);
        URI uri = null;

        for (int i = 0; i < 3; i++) {
            uri = new URI("/question/" + questions.get(i).getQuestionId());
            ResponseEntity.created(uri).build();
        }
    }

    //TODO testen
    @GetMapping(value = "/giveAnswerOptionsRandom/{i}")
    public List<String> giveAnswerOptionsRandom(@RequestBody List<Question> questions, @PathVariable("i") int i) {
        return questionService.giveAnswerOptionsRandom(questions, i);
    }

    //TODO testen
    @GetMapping(value = "/answeredQuestion/answer/{i}")
    public boolean answeredQuestion(@RequestBody List<Question> questions, @PathVariable("i") int i, @PathVariable("answer") String answer) {
        return questionService.answeredQuestion(answer, questions, i);
    }

    //TODO testen
    @GetMapping(value = "/giveVocabStringRandom/{i}")
    public String giveVocabStringRandom(@RequestBody List<Question> questions, @PathVariable("i") int i) {
        return questionService.giveVocabStringRandom(questions, i);
    }
}

