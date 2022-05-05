package de.htwberlin.kba.user_management.export;

import java.util.List;

public interface UserService {
    /**
     * Show the user a list of users without showing the user himself in the list.
     * @param userId of the current user
     * @return userList of all users except the current user
     */
    List<User> getUserList(Long userId);

    /**
     * Let the user choose an opponent for a new game.
     * @param users List of all users to be able to choose an opponent
     * @return userId of the chosen opponent
     */
    Long chooseUser(List<User> users);

    /**
     * returns the list of every user registered
     * @return List of all users
     */
    List<User> getListOfUsers();
    public Long getId();

}
