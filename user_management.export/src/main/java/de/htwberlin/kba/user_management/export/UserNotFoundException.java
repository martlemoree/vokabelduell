package de.htwberlin.kba.user_management.export;

public class UserNotFoundException extends Exception {

    public UserNotFoundException (String msg) {
        super(msg);
    }
}
