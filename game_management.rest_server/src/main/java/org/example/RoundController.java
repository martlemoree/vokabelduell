package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.user_management.export.User;
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

    @PostMapping(value = "/create/{gameId}")
    public ResponseEntity<Void> startNewRound(@PathVariable("gameId") String gameId) throws URISyntaxException {
        Game game = gameService.getGamebyId(Long.valueOf(gameId));
        Round round = roundService.createRound(game);
        URI uri = new URI("/game/" + round.getRoundId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "changeLastPlayer/{gameId}/{userName}")
    public void changeLastPlayer(@PathVariable("gameId") Long gameId, @PathVariable("userName") String userName) {
        Game game = gameService.getGamebyId(gameId);
        User user = userService.getUserByUserName(userName);

        roundService.changeLastPlayer(game, user);
    }

    @GetMapping(value = "/all")
    public List<Round> getRoundList() {
        List<Round> rounds = roundService.getAllRounds();
        return rounds;
    }

}
