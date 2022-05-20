package de.htwberlin.kba.game_ui.impl;

import de.htwberlin.kba.game_ui.export.GameUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GameUiController implements GameUi {

    // TODO Es gibt versch. DI, welche nehmen wir?
    // TODO: Werden Services genutzt? -> hier und in der bean und in der springimpl der config vermerken
    private GameView view;

    // Constructor-Injection
    @Autowired
    public GameUiController(GameView view) {
        super();
        this.view = view;
    }

    public GameUiController() {
        super();
    }

    // @Autowired
    public void setView(GameView view) {
        this.view = view;
    }

    @Override
    public void run() {
        // holds Game Logic I guess
    }
}
