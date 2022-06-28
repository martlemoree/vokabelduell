package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.Question;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.impl.UserServiceImpl;
import de.htwberlin.kba.vocab_management.export.VocabList;
import de.htwberlin.kba.vocab_management.export.VocabListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/game")
public class GameController
{
    private final GameService service;
    private final UserService userService;
    private final VocabListService vocabListService;


    @Autowired
    public GameController(GameService service, UserServiceImpl userService, VocabListService vocabListService) {
        this.service = service;
        this.userService = userService;
        this.vocabListService = vocabListService;
    }

    @PostMapping(value ="/addGame")
    public String createGame(@RequestBody Game game) {
        service.createGame(game);
        return "Game created successfully";
    }

    @PutMapping(value ="/calculatePoints/{gameId}/{userName}/{points}")
    public void calculatePoints(@PathVariable("userId") String userName,
                                @PathVariable("points") int points, @RequestBody Game game) {

        service.calculatePoints(game, userService.getUserByUserName(userName), points);
    }

    @GetMapping("/gamesOfUser/{name}")
    public List<Game> getGamesFromCurrentUser(@PathVariable("name") String name){
        User user = userService.getUserByUserName(name);
        return service.getGamesFromCurrentUser(user);
    }

    @GetMapping("/getQuestions/{gameId}/{userName}/{vocablistId}")
    public List<Question> giveQuestions(@PathVariable("userName") String userName, @PathVariable("vocablistId") Long vocablistId,
                                        @RequestBody Game game){
        User user = userService.getUserByUserName(userName);
        VocabList vlist = vocabListService.getVocabListById(vocablistId);

        return service.giveQuestions(game, user, vlist);
    }



}
