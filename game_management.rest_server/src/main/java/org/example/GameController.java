package org.example;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListObjectNotFoundException;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/game")
public class GameController
{
    private final GameService gameService;
    private final UserService userService;
    private final VocabListService vocabListService;
    private final RoundService roundService;

    @Autowired
    public GameController(GameService service, UserService userService, VocabListService vocabListService, RoundService roundService) {
        this.gameService = service;
        this.userService = userService;
        this.vocabListService = vocabListService;
        this.roundService = roundService;
    }

    @PostMapping(value = "/create/{reqName}/{recName}")
    public ResponseEntity<Long> createGame(@PathVariable("reqName") String reqName, @PathVariable("recName") String recName) throws UserNotFoundException, URISyntaxException {

        User requester = userService.getUserByUserName(reqName);
        User receiver = userService.getUserByUserName(recName);
        Long gameId = gameService.createGame(requester, receiver);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "tokenValue");

        return ResponseEntity.ok().headers(headers).body(gameId);
    }

    @PutMapping(value = "calculatePoints/{gameId}/{userName}/{points}")
    public void calculatePoints(@PathVariable("gameId") Long gameId, @PathVariable("userName") String userName, @PathVariable("points") int points) throws UserNotFoundException, CustomObjectNotFoundException, CustomOptimisticLockExceptionGame {
        gameService.calculatePoints(gameId, userName, points);
    }

    @GetMapping("/gamesOfUser/{name}")
    public List<Game> getGamesFromCurrentUser(@PathVariable("name") String name) throws UserNotFoundException {
        return gameService.getGamesFromCurrentUser(name);
    }

    @GetMapping("/getQuestions/{gameId}/{userName}/{vocablistId}")
    public ResponseEntity<Long> giveQuestions(@PathVariable("userName") String userName, @PathVariable("vocablistId") Long vocablistId,
                                        @PathVariable Long gameId) throws UserNotFoundException, VocabListObjectNotFoundException, CustomObjectNotFoundException, CustomOptimisticLockExceptionGame {

        HttpHeaders headers = new HttpHeaders();
        User user = userService.getUserByUserName(userName);
        VocabList vlist = vocabListService.getVocabListById(vocablistId);
        List<List<String>> questions = gameService.giveQuestions(Long.valueOf(gameId), userName, vocablistId);

        List<String> questions1 = questions.get(0);
        List<String> questions2 = questions.get(1);
        List<String> questions3 = questions.get(2);

        headers.add("question1", questions1.get(0));
        headers.add("question1answer1", questions1.get(1));
        headers.add("question1answer2", questions1.get(2));
        headers.add("question1answer3", questions1.get(3));
        headers.add("question1answer4", questions1.get(4));
        headers.add("questionId1", questions1.get(5));

        headers.add("question2", questions1.get(0));
        headers.add("question2answer1", questions1.get(1));
        headers.add("question2answer2", questions1.get(2));
        headers.add("question2answer3", questions1.get(3));
        headers.add("question2answer4", questions1.get(4));
        headers.add("questionId2", questions1.get(5));

        headers.add("question3",questions1.get(0));
        headers.add("question3answer1", questions1.get(1));
        headers.add("question3answer2", questions1.get(2));
        headers.add("question3answer3", questions1.get(3));
        headers.add("question3answer4", questions1.get(4));
        headers.add("questionId3", questions1.get(5));


        return ResponseEntity.ok().headers(headers).body(gameId);
    }

    @GetMapping(value = "/giveAnswerOptionsRandom/{questionId}")
    public List<String> giveAnswerOptionsRandom(@PathVariable("questionId") Long questionId) throws CustomObjectNotFoundException {
        return gameService.giveAnswerOptionsRandom(questionId);
    }



    @GetMapping(value = "/giveVocabStringRandom/{questionId}")
    public String giveVocabStringRandom(@PathVariable("questionId") Long questionId) throws CustomObjectNotFoundException {
        return gameService.giveVocabStringRandom(questionId);
    }

    @GetMapping(value = "/all")
    public List<Game> getGameList() {
        List<Game> games = gameService.getALlGames();
        return games;
    }

    // FIXME macht den "Could not write JSON: failed to lazily initialize a collection"-Fehler
    @GetMapping(value = "/{gameId}")
    public Game getGameById(@PathVariable("gameId") Long gameId) throws CustomObjectNotFoundException {
        Game game = gameService.getGamebyId(gameId);
        return game;
    }



}
