package de.htwberlin.kba.user_management.export;

import javax.naming.InvalidNameException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    /**
     * Shows the user a list of users without showing the user himself in the list.
     * @param user current user
     * @return userList of all users except the current user
     * @throws UserNotFoundException is thrown when the user cannot be found
     */
    List<User> getUserListWOcurrentUser(User user) throws UserNotFoundException;

    /**
     * Gives back list of all registered users
     * @return userList of all users
     */
    List<User> getUserList();

    /**
     * Offers functionality for user to change password according to given standards
     * @param userName id of User who wants to change password
     * @param password chosen password from the user
     * @throws UserNotFoundException is thrown when the user cannot be found
     */
    void changePassword(String userName, String password) throws UserNotFoundException;

    /**
     * creates new user with given user input
     * @param name user name chosen by current user
     * @param password password chosen by current user
     * @return new user created
     * @throws UserAlreadyExistsException is thrown when an existing user in the database already has the same name and password
     */
    User createUser(String name, String password) throws UserAlreadyExistsException;

    /**
     * holds logic to get user by userName
     * @param userName to identify the user
     * @return the user that belongs to the given userName
     * @throws UserNotFoundException is thrown when the user cannot be found
     */
    User getUserByUserName(String userName) throws UserNotFoundException;

    /**
     * a user is deleted in the data base
     * @param name of the user that should be deleted
     * @throws UserNotFoundException is thrown when the User does not exist
     */
    void removeUserName(String name) throws UserNotFoundException;
}
