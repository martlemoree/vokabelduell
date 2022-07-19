package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    public QuestionService service;

    @Autowired
    public QuestionController(QuestionService service) {
        this.service = service;
    }

    /*
    @GetMapping("/getAllAnswers/{questionId}/{}")
    public List<Translation> getAllAnswers(@RequestBody Question question){
        return service.getAllAnswers(question);
    }*/


    @PostMapping(value ="/add")
    public ResponseEntity<Void> createQuestion(@RequestBody Question question) throws URISyntaxException {
        Question newQuest = service.createQuestion(question.getRound(), question.getRound().getVocablist());
        URI uri = new URI("/question/" + newQuest.getQuestionId());
        return ResponseEntity.created(uri).build();
    }

    /*
    @PostMapping(value ="/giveAnswerOptionsRandom")
    public List<String> giveAnswerOptionsRandom(@RequestBody Question question) {
        return service.giveAnswerOptionsRandom(question);
    }*/

    /*
    @PutMapping(value ="/answerQuestion/{answer}/{rightAnswer}")
    public boolean answeredQuestion(@PathVariable("answer") String answer, @PathVariable("rightAnswer") Translation rightAnswer) {
        return service.answeredQuestion(answer, rightAnswer);
    }*/


}



  /*  private final QuestionService service;
    private final VocabListService vocabListService;

    //    public Translation setAnswerOptions() {
    // TODO macht das hier überhaupt sinn lol
    public QuestionController(QuestionService service, VocabListService vocabListService) {
        this.service = service;
        this.vocabListService = vocabListService;
    }

    @PostMapping(value ="/addQuestion")
    public void createGame(@RequestBody Question question) {
        service.createQuestion(question);
    }

    // TODO Antje hier auch sorry
    @GetMapping("/getAllAnswers")
    public List<Translation> getAllAnswers(@RequestBody Question question){
        return service.getAllAnswers(question);
    }
    @PostMapping(value ="/createQuestions/{vlistId}")
    public List<Question> createQuestions(@RequestBody Game game, @PathVariable("vlist") Long vlistId, @RequestBody Round round) {
        VocabList vlist = vocabListService.getVocabListById(vlistId);
        return service.createQuestions(game, vlist, round);
    }

    // TODO Antje; sorry musste wegen Kempas feedback die methode ändern und will hier nichts kaputt machen

    // TODO Antje sorry hier auch
    */


