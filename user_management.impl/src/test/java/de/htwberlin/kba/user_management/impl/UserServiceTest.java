package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    UserDao userDao;
    @InjectMocks
    private UserServiceImpl service;

    @Test
    @DisplayName("getUserList gives back return parameter")
    public void testGetUserListWOcurrentUserNotEmpty(){
        // 1. Arrange
        List<User> users = new ArrayList<>();
        Long exampleId = 123456L;
        users.add(new User(exampleId, "AntjeWinner", "StellaIstToll"));
        users.add(new User(456789L, "MartinTheBrain", "IchLiebeKBA"));
        //ToDo datenbankzugriff

        //Mockito.when(UserDao.setAnswerOptions()).thenReturn(users);

        // 2. Act
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser("AntjeWinner");

        // 3. Assert
        assertNotNull(usersWithoutCurrentUser);
    }

    @Test
    @DisplayName("get all users without current user")
    public void testGetUserListWOcurrentUser(){
        // 1. Arrange
        List<User> users = new ArrayList<>();
        Long exampleId = 123456L;
        users.add(new User(exampleId, "AntjeWinner", "StellaIstToll"));
        users.add(new User(456789L, "MartinTheBrain", "IchLiebeKBA"));
        //ToDo datenbankzugriff

        boolean bol = false;

        // 2. Act
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser("AntjeWinner");

        for (int i = 0; i < usersWithoutCurrentUser.size(); i++) {
            if (!exampleId.equals(usersWithoutCurrentUser.get(i).getUserId())) {
                bol = true;
                break;
            }
            i++;
        }

        // 3. Assert
        //assertTrue(bol);
    }


    @Test
    @DisplayName("user chooses a new password")
    public void changePassword() {
        // 1. Arrange
        User user = new User(123456L, "AntjeWinner", "StellaIstToll");
        String newPassword = "QWERTZ";

        // TODO datenbankzugriff
        // 2. Act
        service.changePassword(newPassword, user);

        // 3. Assert
        assertEquals(newPassword, user.getPassword());

    }


    @Test
    @DisplayName("Method returns a User")
    public void testGetUserByUsername() {
        // 1. Arrange
        User user = new User(123456L, "AntjeWinner", "StellaIstToll");
        //TODO datenbankzugriff


        // 3. Assert
        assertNotNull(service.getUserByUserName("AntjeWinner"));
        assertEquals(service.getUserByUserName("AntjeWinner").getUserName(), user.getUserName());
        assertEquals(service.getUserByUserName("AntjeWinner").getPassword(), user.getPassword());

    }

}
