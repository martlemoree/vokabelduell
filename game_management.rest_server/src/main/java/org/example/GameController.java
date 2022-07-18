package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.impl.GameServiceImpl;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.vocab_management.export.VocabList;
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

    @Autowired
    public GameController(GameService service, UserService userService, VocabListService vocabListService) {
        this.gameService = service;
        this.userService = userService;
        this.vocabListService = vocabListService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createGame(@RequestBody Request request) throws URISyntaxException, URISyntaxException {
        Game newGame = gameService.createGame(request);
        URI uri = new URI("/game/" + newGame.getGameId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "calculatePoints/{gameId}/{userName}/{points}")
    public ResponseEntity<?> calculatePoints(@PathVariable("gameId") String gameId, @PathVariable("userName") String userName, @PathVariable("points") String points) {
        User user = userService.getUserByUserName(userName);
        Game game = gameService.getGamebyId(Long.valueOf(gameId));

        gameService.calculatePoints(game, user, Integer.parseInt(points));
        return game != null? ResponseEntity.ok(game) : ResponseEntity.notFound().build();
    }

    @GetMapping("/gamesOfUser/{name}")
    public List<Game> getGamesFromCurrentUser(@PathVariable("name") String name){
        User user = userService.getUserByUserName(name);
        return gameService.getGamesFromCurrentUser(user);
    }

    @GetMapping("/getQuestions/{gameId}/{userName}/{vocablistId}")
    public List<Question> giveQuestions(@PathVariable("userName") String userName, @PathVariable("vocablistId") Long vocablistId,
                                        @PathVariable String gameId){
        Game game = gameService.getGamebyId(Long.valueOf(gameId));
        User user = userService.getUserByUserName(userName);
        VocabList vlist = vocabListService.getVocabListById(vocablistId);

        return gameService.giveQuestions(game, user, vlist);
    }

    @GetMapping(value = "/all")
    public List<Game> getUserList() {
        List<Game> requests = gameService.getALlGames();
        return requests;
    }





}
