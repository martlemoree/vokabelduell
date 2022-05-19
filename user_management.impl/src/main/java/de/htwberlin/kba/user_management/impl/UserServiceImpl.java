package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.UserService;
import de.htwberlin.kba.user_management.export.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    //todo Bitte Methode zum Erstellen und Löschen eines User ergänzen
    //- Die restlichen Methoden evtl. nicht weiter betrachten

    private static List<User> users;
    public List<User> getUserListWOcurrentUser(Long userId) {

        List<User>listWOuser = new ArrayList<>(users);
        listWOuser.remove(getUserById(userId));
        return listWOuser;
    }
    public Long chooseUser() {
        //ToDo keeeinee ahnung
        return null;
    }
    public void changePassword(String password, User user) {
        user.setPassword(password);
    }

    public void addUsertoList(User user){
        this.users.add(user);
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(Long Id){
        User returnUser;

        for (User u : users){
            if (u.getUserId() == Id){
                return u;
            }
        }
        return null;
    }
}
