package de.htwberlin.kba.vokabelduell_ui.impl;

import de.htwberlin.kba.vokabelduell_ui.export.VokabellduellUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class VokabellduellUiController implements VokabellduellUi {

    // TODO Es gibt versch. DI, welche nehmen wir?
    // TODO: Werden Services genutzt? -> hier und in der bean und in der springimpl der config vermerken
    private VokabelduellView view;

    // Constructor-Injection
    @Autowired
    public VokabellduellUiController(VokabelduellView view) {
        super();
        this.view = view;
    }

    public VokabellduellUiController() {
        super();
    }

    // @Autowired
    public void setView(VokabelduellView view) {
        this.view = view;
    }

    @Override
    public void run() {
        // holds Game Logic I guess
    }
}
