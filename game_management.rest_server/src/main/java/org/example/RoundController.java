package org.example;

import de.htwberlin.kba.game_management.export.RoundService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/round")
public class RoundController {

    private final RoundService service;

    public RoundController(RoundService service) {
        this.service = service;
    }

    //TODO welche methoden muss ich hier implementieren?
}
