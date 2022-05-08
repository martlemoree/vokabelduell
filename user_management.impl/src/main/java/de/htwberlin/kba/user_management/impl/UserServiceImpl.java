package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    public List<User> getUserListWOcurrentUser(Long userId, List<User> users) {
        return null;
    }
    public Long chooseUser(List<User> users) {
        return null;
    }
    public void changePassword(String password, User user) {}
}
