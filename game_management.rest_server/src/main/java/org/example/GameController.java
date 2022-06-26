package org.example;

import de.htwberlin.kba.game_management.export.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game")
public class GameController
{
    private final GameService service;


    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    //TODO welche methoden muss ich hier implementieren?
}
