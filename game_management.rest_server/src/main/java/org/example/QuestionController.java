package org.example;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.vocab_management.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
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


    @PostMapping(value = "/create/{roundId}/{vocablistId}")
    public ResponseEntity<Void> createQuestion(@PathVariable("roundId") Long roundId, @PathVariable("vocablistId") Long vocablistId) throws URISyntaxException, VocabListNotFoundException {
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

    @GetMapping(value = "/getAllAnswers/{questionId}")
    public List<Translation> getAllAnswers(@PathVariable("questionId") Long questionId) {
        return questionService.getAllAnswers(questionService.getQuestionById(questionId));
    }

    @PostMapping(value = "/createQuestions/{gameId}/{vocablistId}/{roundId}")
    public void createQuestions(@PathVariable("gameId") Long gameId, @PathVariable("vocablistId") Long vocablistId, @PathVariable("roundId") Long roundId) throws URISyntaxException, VocabListNotFoundException {
        Round round = roundService.getRoundById(roundId);
        VocabList vocabList = vocabListService.getVocabListById(vocablistId);
        Game game = gameService.getGamebyId(gameId);

        List<Question> questions = questionService.createQuestions(game, vocabList, round);
        URI uri;

        for (int i = 0; i < 3; i++) {
            uri = new URI("/question/" + questions.get(i).getQuestionId());
            ResponseEntity.created(uri).build();
        }
    }

    // TODO hier gibt es probleme wenn die answer options leer sind
    @GetMapping(value = "/giveAnswerOptionsRandom/{questionId}")
    public List<String> giveAnswerOptionsRandom(@PathVariable("questionId") Long questionId) {
        return questionService.giveAnswerOptionsRandom(questionService.getQuestionById(questionId));
    }

    // TODO hier gibt es probleme wenn die answer options leer sind
    @GetMapping(value = "/giveVocabStringRandom/{questionId}")
    public String giveVocabStringRandom(@PathVariable("questionId") Long questionId) {
        return questionService.giveVocabStringRandom(questionService.getQuestionById(questionId));
    }


    @GetMapping(value = "/answeredQuestion/{answer}/{questionId}")
    public boolean answeredQuestion(@PathVariable("questionId") Long questionId, @PathVariable("answer") String answer) {
        return questionService.answeredQuestion(answer, questionService.getQuestionById(questionId));
    }
}

