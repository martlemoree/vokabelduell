package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // TODO hier und im Folgenden Datenbankzugriff einfügen
    private static List<User> users;
    public List<User> getUserListWOcurrentUser(Long userId) {

        List<User>listWOuser = new ArrayList<>(users);
        listWOuser.remove(getUserById(userId));
        return listWOuser;
    }
    public Long chooseUser() {
        //todo Benutzereingabe
        return null;
    }
    public void changePassword(String password, User user) {
        user.setPassword(password);
    }

    public User getUserById(Long Id){
        // TODO DAO mit find
        User returnUser;

        for (User u : users){
            if (u.getUserId().equals(Id)){
                return u;
            }
        }
        return null;
    }

    // TODO: muss das noch in die DAO/Impl?
    public User getUserByUserName(String userName){
        User returnUser;

        for (User u : users){
            if (u.getUserName().equals(userName)){
                return u;
            }
        }
        return null;
    }

    public User createUser(String name, String password){
        //todo automatische ID vergeben
        User u = new User(1L, name, password);
        this.users.add(u);
        return u;
    }

    public void removeUser(User user){
        // todo User aus der DB löschen
    }
}
