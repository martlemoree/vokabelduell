package de.htwberlin.kba.vokabelduell_ui.export;

import de.htwberlin.kba.user_management.export.UserAlreadyExistsException;

import java.io.FileNotFoundException;

public interface VokabellduellUi {

    /**
     * holds the game logic.
     * @throws FileNotFoundException is thrown when the file in given path does not exist
     * @throws UserAlreadyExistsException is thrown when the name is already used for a different user.
     */
    void run() throws FileNotFoundException, UserAlreadyExistsException;
}
