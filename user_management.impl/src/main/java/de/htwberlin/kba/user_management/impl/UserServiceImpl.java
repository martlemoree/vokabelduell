package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<User> getUserListWOcurrentUser(String name) {
        List<User>listWOuser = userDao.getAllUsers();
        listWOuser.remove(getUserByUserName(name));
        return listWOuser;
    }

    public void changePassword(String password, User user) {
        // method not implemented and tested because it is not part of the game logic
        user.setPassword(password);
    }


    public User getUserByUserName(String userName){
        return userDao.getUserByName(userName);
    }

    public User createUser(String name, String password){
        User u = new User(name, password);
        userDao.createUser(u);
        return u;
    }

    public void removeUser(User user){
        // method not implemented and tested because it is not part of the game logic
    }
}
