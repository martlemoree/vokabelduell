package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.CustomOptimisticLockExceptionUser;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void createUser(User user);

    User getUserById(Long userId) throws UserNotFoundException;

    User getUserByName(String name) throws UserNotFoundException;

    void updateUser(User user) throws CustomOptimisticLockExceptionUser;

    List<User> getAllUsers();

    void deleteUserId(Long id) throws UserNotFoundException;
}
