package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserDao userDao;
    private UserService service = new UserServiceImpl(userDao);

    @Test
    @DisplayName("getUserList gives back return parameter")
    public void testGetUserListWOcurrentUserNotEmpty(){
        // 1. Arrange
        List<User> users = new ArrayList<>();
        Long exampleId = 123456L;
        users.add(new User("AntjeWinner", "StellaIstToll"));
        users.add(new User("MartinTheBrain", "IchLiebeKBA"));
        //ToDo datenbankzugriff

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
        users.add(new User("AntjeWinner", "StellaIstToll"));
        users.add(new User("MartinTheBrain", "IchLiebeKBA"));
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
        User user = new User("AntjeWinner", "StellaIstToll");
        String newPassword = "QWERTZ";

        // 2. Act
        service.changePassword(newPassword, user);

        // 3. Assert
        assertEquals(newPassword, user.getPassword());

    }


    @Test
    @DisplayName("Method returns a User")
    public void testGetUserByUsername() {
        // 1. Arrange
        User user = new User("AntjeWinner", "StellaIstToll");
        //TODO datenbankzugriff


        // 3. Assert
        assertNotNull(service.getUserByUserName("AntjeWinner"));
        assertEquals(service.getUserByUserName("AntjeWinner").getUserName(), user.getUserName());
        assertEquals(service.getUserByUserName("AntjeWinner").getPassword(), user.getPassword());

    }

}
