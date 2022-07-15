package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserAlreadyExistsException;
import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        super();
        this.userDao = userDao;
    }

    // constructor without parameters needed for mockito tests
    public UserServiceImpl(){}

    @Transactional
    public List<User> getUserListWOcurrentUser(User user) {
        List<User>listWOuser = userDao.getAllUsers();
        listWOuser.remove(user);
        return listWOuser;
    }

    @Transactional
    public List<User> getUserList() {
        List<User> listOfUsers = userDao.getAllUsers();
        return listOfUsers;
    }

    public void changePassword(String password, User user) {
        // method not implemented and tested because it is not part of the game logic
        user.setPassword(password);
    }

    @Transactional
    public User getUserByUserName(String userName) throws EntityNotFoundException {
        // method not tested because it is not part of the game logic
        return userDao.getUserByName(userName);
    }

    @Transactional
    public User createUser(String name, String password) throws InvalidNameException, SQLException, UserAlreadyExistsException {
        // method not tested because it would only be a database test
        User u = new User(name, password);
        userDao.createUser(u);
        return u;
    }

    public boolean removeUser(User user){
        if (!userDao.getAllUsers().contains(userDao.getUserById(user.getUserId()))){
            return false;
        }

        userDao.deleteUser(user);
        return true;
    }

    public User testAPI(String Name){
        return new User(Name, "ES GEHT");
    }
}
