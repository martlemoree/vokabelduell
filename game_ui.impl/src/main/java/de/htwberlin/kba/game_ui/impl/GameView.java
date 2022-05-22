package de.htwberlin.kba.game_ui.impl;

import org.springframework.stereotype.Component;

@Component
public class GameView {

    public void byeBye() {
        System.out.println("Bye :)");
    }

    public void welcome() {
        System.out.println("Anwendung gestartet");
    }
}
