package de.htwberlin.kba.user_management.export;

import javax.naming.InvalidNameException;
import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * Shows the user a list of users without showing the user himself in the list.
     * @param user current user
     * @return userList of all users except the current user
     */
    List<User> getUserListWOcurrentUser(User user);

    /**
     * Gives back list of all registered users
     * @return userList of all users
     */
    List<User> getUserList();

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
    User createUser(String name, String password) throws InvalidNameException, SQLException, UserAlreadyExistsException;

    /**
     * holds logic to get user by userName
     * @param userName to identify the user
     * @return the user that belongs to the given userName
     */
    User getUserByUserName(String userName) throws EntityNotFoundException;

    /**
     * holds logic to remove given user
     * @param user which should be removed
     */
    boolean removeUser(User user);

     User testAPI(String Name);

}
