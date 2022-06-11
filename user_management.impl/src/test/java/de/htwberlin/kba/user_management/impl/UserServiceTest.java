package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Spy
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserDaoImpl userDao;

    private User u1;
    private User u2;
    private List<User> users = new ArrayList<>();

    @Before
    public void setUp(){
        u1 = new User("AntjeWinner", "StellaIstToll");
        u2 = new User( "MartinTheBrain", "IchLiebeKBA");
        this.users.add(u1);
        this.users.add(u2);
    }

    @Test
    @DisplayName("getUserList gives back return parameter")
    public void testGetUserListWOcurrentUserNotEmpty(){
        // 1. Arrange
        String name = "AntjeWinner";

        // 2. Act
        Mockito.when(userDao.getAllUsers()).thenReturn(this.users);
        Mockito.when(userDao.getUserByName(name)).thenReturn(this.u1);
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser(name);

        // 3. Assert
        assertNotNull(usersWithoutCurrentUser);
        assertEquals(1, usersWithoutCurrentUser.size());
    }

    @Test
    @DisplayName("get all users without current user")
    public void testGetUserListWOcurrentUser(){
        // 1. Arrange
        String example_name = "AntjeWinner";
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

}
