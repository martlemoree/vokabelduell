package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.GameService;
import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.game_management.export.RoundService;
import de.htwberlin.kba.user_management.export.User;
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

    @Autowired
    public RoundController(RoundService roundService, GameService gameService) {
        this.roundService = roundService;
        this.gameService = gameService;
    }

    @PostMapping(value = "/create/{gameId}")
    public ResponseEntity<Void> startNewRound(@PathVariable("gameId") String gameId) throws URISyntaxException {
        Game game = gameService.getGamebyId(Long.valueOf(gameId));
        Round round = roundService.startNewRound(game);
        URI uri = new URI("/game/" + round.getRoundId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/all")
    public List<Round> getRoundList() {
        List<Round> rounds = roundService.getAllRounds();
        return rounds;
    }

}
