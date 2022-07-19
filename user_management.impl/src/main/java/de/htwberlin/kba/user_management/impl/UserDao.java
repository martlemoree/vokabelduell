package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void createUser(User user) throws SQLException;

    User getUserById(Long userId);

    User getUserByName(String name);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteUser(User user);


    void deleteUserId(Long id);
}
