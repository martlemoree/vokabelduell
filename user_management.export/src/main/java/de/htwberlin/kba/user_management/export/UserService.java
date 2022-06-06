package de.htwberlin.kba.user_management.export;

import java.util.List;

public interface UserService {
    /**
     * Show the user a list of users without showing the user himself in the list.
     * @param name of the current user
     * @return userList of all users except the current user
     */
    List<User> getUserListWOcurrentUser(String name);



    /**
     * Offers functionality for user to change password according to given standards
     * @param password chosen password from the user
     * @param user User who wants to change password
     */
    void changePassword(String password, User user);

    /**
     * creates new user with given user input
     * @param name user name chosen by current user
     * @param password password chosen by current user
     * @return new user created
     */
    User createUser(String name, String password);

    /**
     * holds logic to get user by userName
     * @param userName to identify the user
     * @return the user that belongs to the given userName
     */
    User getUserByUserName(String userName);

    /**
     * holds logic to remove given user
     * @param user which should be removed
     */
    void removeUser(User user);

}
