package de.htwberlin.kba.user_management.export;

import java.util.List;

public interface UserService {
    /**
     * Show the user a list of users without showing the user himself in the list.
     * @param userId of the current user
     * @param users List of all registered users
     * @return userList of all users except the current user
     */
    List<User> getUserListWOcurrentUser(Long userId, List<User> users);

    /**
     * Let the user choose an opponent for a new game.
     * @param users List of all users to be able to choose an opponent
     * @return userId of the chosen opponent
     */
    Long chooseUser(List<User> users);

    /**
     * Offers functionality for user to change password according to given standards
     * @param password chosen password from the user
     * @param user User who wants to change password
     */
    void changePassword(String password, User user);

}
