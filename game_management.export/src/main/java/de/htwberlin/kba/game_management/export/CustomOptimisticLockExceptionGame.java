package de.htwberlin.kba.game_management.export;

public class CustomOptimisticLockExceptionGame extends Exception {

    public CustomOptimisticLockExceptionGame(String msg) {
        super(msg);
    }
}
