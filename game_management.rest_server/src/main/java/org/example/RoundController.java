package org.example;

import de.htwberlin.kba.game_management.export.*;
import de.htwberlin.kba.user_management.export.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/round")
public class RoundController {

    private final RoundService roundService;
    private final GameService gameService;
    private final UserService userService;

    @Autowired
    public RoundController(RoundService roundService, GameService gameService, UserService userService) {
        this.roundService = roundService;
        this.gameService = gameService;
        this.userService = userService;
    }

    @PostMapping(value = "/startNewRound/{gameId}")
    public ResponseEntity<Void> startNewRound(@PathVariable("gameId") String gameId) throws URISyntaxException, CustomObjectNotFoundException, CustomOptimisticLockExceptionGame {
        Game game = gameService.getGamebyId(Long.valueOf(gameId));
        Round round = roundService.startNewRound(game);
        URI uri = new URI("/game/" + round.getRoundId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "changeLastPlayer/{gameId}/{userName}")
    public void changeLastPlayer(@PathVariable("gameId") Long gameId, @PathVariable("userName") String userName) throws CustomObjectNotFoundException, CustomOptimisticLockExceptionGame {
        roundService.changeLastPlayer(gameId, userName);
    }

    @GetMapping(value = "/all")
    public List<Round> getRoundList() {
        List<Round> rounds = roundService.getAllRounds();
        return rounds;
    }

}
