package de.htwberlin.kba.vokabelduell_ui.export;

import de.htwberlin.kba.user_management.export.UserAlreadyExistsException;

import java.io.FileNotFoundException;

public interface VokabellduellUi {

    void run() throws FileNotFoundException, UserAlreadyExistsException;
}
