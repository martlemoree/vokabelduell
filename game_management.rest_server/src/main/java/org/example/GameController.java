package org.example;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListNotFoundException;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /* MIT DIESER METHODE WERDEN DIE USER DOPPELT ANGELEGT
    @PostMapping(value = "/create")
    public ResponseEntity<Void> createGame(@RequestBody Request request) throws URISyntaxException, URISyntaxException {
        Game newGame = gameService.createGame(request);
        URI uri = new URI("/game/" + newGame.getGameId());
        return ResponseEntity.created(uri).build();
    } */

    @PostMapping(value = "/create/{reqName}/{recName}")
    public ResponseEntity<Void> createGame(@PathVariable("reqName") String reqName, @PathVariable("recName") String recName) throws UserNotFoundException, URISyntaxException {

        User requester = userService.getUserByUserName(reqName);
        User receiver = userService.getUserByUserName(recName);
        Game newGame = gameService.createGame(requester, receiver);
        URI uri = new URI("/game/" + newGame.getGameId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "calculatePoints/{gameId}/{userName}/{points}")
    public void calculatePoints(@PathVariable("gameId") Long gameId, @PathVariable("userName") String userName, @PathVariable("points") int points) throws UserNotFoundException, CustomObjectNotFoundException, CustomOptimisticLockExceptionGame {
        gameService.calculatePoints(gameId, userName, points);
    }

    // TODO JsonMappingException irgendwas mit lazy // jetzt klappts irgendwie doch
    @GetMapping("/gamesOfUser/{name}")
    public List<Game> getGamesFromCurrentUser(@PathVariable("name") String name) throws UserNotFoundException {
        return gameService.getGamesFromCurrentUser(name);
    }

    //TODO testen
    @GetMapping("/getQuestions/{gameId}/{userName}/{vocablistId}")
    public List<Question> giveQuestions(@PathVariable("userName") String userName, @PathVariable("vocablistId") Long vocablistId,
                                        @PathVariable String gameId) throws UserNotFoundException, VocabListNotFoundException, CustomObjectNotFoundException, CustomOptimisticLockExceptionGame {
        Game game = gameService.getGamebyId(Long.valueOf(gameId));
        User user = userService.getUserByUserName(userName);
        VocabList vlist = vocabListService.getVocabListById(vocablistId);

        return gameService.giveQuestions(game, user, vlist);
    }

    @GetMapping(value = "/all")
    public List<Game> getGameList() {
        List<Game> games = gameService.getALlGames();
        return games;
    }

    // FIXME macht den "Could not write JSON: failed to lazily initialize a collection"-Fehler
    @GetMapping(value = "/{gameId}")
    public Game getGameById(@PathVariable("gameId") int gameId) throws CustomObjectNotFoundException {
        Game game = gameService.getGamebyId((long) gameId);
        return game;
    }



}
