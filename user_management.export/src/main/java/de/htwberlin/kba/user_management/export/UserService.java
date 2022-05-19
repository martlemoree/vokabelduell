package de.htwberlin.kba.user_management.export;

import java.util.List;

public interface UserService {
    /**
     * Show the user a list of users without showing the user himself in the list.
     * @param userId of the current user
     * @return userList of all users except the current user
     */
    List<User> getUserListWOcurrentUser(Long userId);

    /**
     * Let the user choose an opponent for a new game.
     * @return userId of the chosen opponent
     */
    Long chooseUser();

    /**
     * Offers functionality for user to change password according to given standards
     * @param password chosen password from the user
     * @param user User who wants to change password
     */
    void changePassword(String password, User user);

    void setUsers(List<User> users);

    List<User> getUsers();

    User createUser(String name, String password);

    User getUserById(Long Id);
    void removeUser(User user);

}
