package de.htwberlin.kba.user_management.export;

import javax.naming.AuthenticationException;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
