package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.QuestionService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.vocab_management.export.Translation;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class QuestionController {

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

    @GetMapping("/getAllAnswers")
    public List<Translation> getAllAnswers(@RequestBody Question question){
        return service.getAllAnswers(question);
    }
    @PostMapping(value ="/createQuestions/{vlistId}")
    public List<Question> createQuestions(@RequestBody Game game, @PathVariable("vlist") Long vlistId, @RequestBody Round round) {
        VocabList vlist = vocabListService.getVocabListById(vlistId);
        return service.createQuestions(game, vlist, round);
    }
    @PostMapping(value ="/giveAnswerOptionsRandom")
    public List<String> giveAnswerOptionsRandom(@RequestBody Question question) {
        return service.giveAnswerOptionsRandom(question);
    }
    @PutMapping(value ="/answerQuestion/{answer}/{rightAnswer}")
    public boolean answeredQuestion(@PathVariable("answer") String answer, @PathVariable("rightAnswer") Translation rightAnswer) {
        return service.answeredQuestion(answer, rightAnswer);
    }*/

}
