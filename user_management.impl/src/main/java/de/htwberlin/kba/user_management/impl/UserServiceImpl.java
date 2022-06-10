package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        super();
        this.userDao = userDao;
    }

    // TODO hier und im Folgenden Datenbankzugriff einfügen
    private static List<User> users;
    public List<User> getUserListWOcurrentUser(String name) {

        List<User>listWOuser = new ArrayList<>(users);
        listWOuser.remove(getUserByUserName(name));
        return listWOuser;
    }

    public void changePassword(String password, User user) {
        user.setPassword(password);
    }


    // TODO: muss das noch in die DAO/Impl?
    public User getUserByUserName(String userName){

        for (User u : users){
            if (u.getUserName().equals(userName)){
                return u;
            }
        }
        return null;
    }

    public User createUser(String name, String password){
        //TODO automatische ID vergeben
        User u = new User(name, password);
        this.users.add(u);
        return u;
    }

    public void removeUser(User user){
        // TODO User aus der DB löschen
    }
}
