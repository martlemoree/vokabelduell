package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    /**
     * Show the user a list of users without showing the user himself in the list.
     * @param userId of the current user
     * @return userList of all users except the current user
     */
    public List<User> getUserListWOcurrentUser(Long userId, List<User> users) {
        return null;
    }

    /**
     * Let the user choose an opponent for a new game.
     * @param users List of all users to be able to choose an opponent
     * @return userId of the chosen opponent
     */
    public Long chooseUser(List<User> users) {
        return null;
    }

    public List<User> getListOfUsers() {

        return null;
    }

    public Long getId() {

        return null;
    }
}
