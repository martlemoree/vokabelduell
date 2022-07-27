package de.htwberlin.kba.user_management.export;

import javax.naming.InvalidNameException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    /**
     * Shows the user a list of users without showing the user himself in the list.
     * @param userName current user
     * @return userList of all users except the current user
     * @throws UserNotFoundException is thrown when the User does not exist
     */
    List<User> getUserListWOcurrentUser(String userName) throws UserNotFoundException;

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
     * @throws UserAlreadyExistsException is thrown when the user name already exists in the database
     */
    User createUser(String name, String password) throws UserAlreadyExistsException;

    /**
     * holds logic to get user by userName
     * @param userName to identify the user
     * @return the user that belongs to the given userName
     * @throws UserNotFoundException is thrown when the User does not exist
     */
    User getUserByUserName(String userName) throws UserNotFoundException;

//    /**
//     * find a user by his id
//     * @param id given id of the user
//     * @return the user with the given id
//     */
    // TODO kann gelöscht werden?
//    User getUserById(Long id) throws UserNotFoundException;

    // TODO Es muss eigentlich removeUserByName sein

    /**
     * a user is deleted in the data base
     * @param name of the user that should be deleted
     * @throws UserNotFoundException is thrown when the User does not exist
     */
    void removeUserName(String name) throws UserNotFoundException;
}
