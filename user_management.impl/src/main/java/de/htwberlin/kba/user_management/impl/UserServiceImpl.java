package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserAlreadyExistsException;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    // constructor without parameters needed for mockito tests
//    public UserServiceImpl(){}

    @Transactional
    public List<User> getUserListWOcurrentUser(String userName) throws UserNotFoundException {
        List<User>listWOuser = userDao.getAllUsers();
        User user = userDao.getUserByName(userName);
        listWOuser.remove(user);
        return listWOuser;
    }

    @Transactional
    public List<User> getUserList() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void changePassword(String password, User user) {
        // method not implemented and tested because it is not part of the game logic
        user.setPassword(password);
    }

    @Transactional
    public User getUserByUserName(String userName) throws UserNotFoundException {
        // method not tested because it is just a getter
        return userDao.getUserByName(userName);
    }

    @Transactional
    public User createUser(String name, String password) throws UserAlreadyExistsException {
        for (User alreadyExistingUser : getUserList()) {
            if (alreadyExistingUser.getUserName().equals(name)) {
                throw new UserAlreadyExistsException("Diesen Benutzernamen gibt es leider schon. Probiere es noch einmal. Drücke enter zum Verlassen des Menüpunkts.");
            }
        }

        User u = new User(name, password);
        userDao.createUser(u);
        return u;
    }

    @Transactional
    public User getUserById(Long id) throws UserNotFoundException {
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (NoResultException e) {
            throw new UserNotFoundException("Der User mit der Id "+id+" konnte nicht gefunden werden.");
        }
        return user;
    }

    @Override
    @Transactional
    public boolean removeUserName(String name) throws UserNotFoundException {
        User user;
        try {
            user = userDao.getUserByName(name);
        } catch (NoResultException e) {
            throw new UserNotFoundException("Der User mit dem Namen "+name+" konnte nicht gefunden werden.");
        }

        userDao.deleteUserId(user.getUserId());
        return true;
    }

}
