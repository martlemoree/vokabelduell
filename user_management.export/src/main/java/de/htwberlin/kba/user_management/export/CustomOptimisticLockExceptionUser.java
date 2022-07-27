package de.htwberlin.kba.user_management.export;

public class CustomOptimisticLockExceptionUser extends Exception {

    public CustomOptimisticLockExceptionUser(String msg) {
        super(msg);
    }
}
