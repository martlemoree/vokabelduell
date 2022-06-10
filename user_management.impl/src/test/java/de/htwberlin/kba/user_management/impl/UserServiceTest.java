package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Spy
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    UserDao userDao;

    User u1;
    List<User> users;
    //getUserListWOcurrentUser --> ist da 2x aber geht nicht
    //getUserByUserName --> braucht man eigtl nicht, mal sollte gleich die DAO aufrufen

    @BeforeAll
    public void setUp(){
        List<User> users = new ArrayList<>();
        User antje = new User("AntjeWinner", "StellaIstToll");
        users.add(new User( "MartinTheBrain", "IchLiebeKBA"));
        users.add(antje);
    }

    @Test
    @DisplayName("getUserList gives back return parameter")
    public void testGetUserListWOcurrentUserNotEmpty(){
        // 1. Arrange
        List<User> result = new ArrayList<>(users);
        result.remove(u1);

        // 2. Act
        Mockito.when(userDao.getUserByName("AntjeWinner")).thenReturn(u1);
        Mockito.when(userDao.getAllUsers()).thenReturn(users);
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser("AntjeWinner");

        // 3. Assert
        assertNotNull(usersWithoutCurrentUser);
        assertEquals(usersWithoutCurrentUser.size(), result.size());
    }

    @Test
    @DisplayName("get all users without current user")
    public void testGetUserListWOcurrentUser(){
        // 1. Arrange
        String example_name = "Antje_Winner";
        boolean bol = true;

        // 2. Act
        Mockito.when(userDao.getUserByName(example_name)).thenReturn(u1);
        Mockito.when(userDao.getAllUsers()).thenReturn(users);
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser(example_name);

        for (int i = 0; i < usersWithoutCurrentUser.size(); i++) {
            if (example_name.equals(usersWithoutCurrentUser.get(i).getUserName())) {
                bol = false;
                break;
            }
            i++;
        }

        // 3. Assert
        Assertions.assertEquals(true, bol);
    }




   /* @Test
    @DisplayName("Method returns a User")
    public void testGetUserByUsername() {
        // 1. Arrange
        User user = new User(123456L, "AntjeWinner", "StellaIstToll");
        //TODO datenbankzugriff


        // 3. Assert
        assertNotNull(service.getUserByUserName("AntjeWinner"));
        assertEquals(service.getUserByUserName("AntjeWinner").getUserName(), user.getUserName());
        assertEquals(service.getUserByUserName("AntjeWinner").getPassword(), user.getPassword());

    }*/

}
