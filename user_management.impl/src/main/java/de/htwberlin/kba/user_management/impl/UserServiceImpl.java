package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<User> getUserListWOcurrentUser(String name) {
        List<User>listWOuser = userDao.getAllUsers();
        User user = this.getUserByUserName(name);
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
    public User getUserByUserName(String userName){
        // method not tested because it is not part of the game logic
        return userDao.getUserByName(userName);
    }

    @Transactional
    public User createUser(String name, String password){
        // method not tested because it is not part of the game logic
        User u = new User(name, password);
        userDao.createUser(u);
        return u;
    }

    public void removeUser(User user){
        // method not implemented and tested because it is not part of the game logic
    }
}
