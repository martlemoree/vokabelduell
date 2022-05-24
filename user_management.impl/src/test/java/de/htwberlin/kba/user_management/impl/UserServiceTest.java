package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService service;
    @BeforeAll
    public void setUp() {
        this.service = new UserServiceImpl();
    }

    @Test
    @DisplayName("getUserList gives back return parameter")
    public void testGetUserListWOcurrentUserNotEmpty(){
        // 1. Arrange
        List<User> users = new ArrayList<>();
        Long exampleId = 123456L;
        users.add(new User(exampleId, "AntjeWinner", "StellaIstToll"));
        users.add(new User(456789L, "MartinTheBrain", "IchLiebeKBA"));
        service.setUsers(users);

        // 2. Act
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser(exampleId);

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
        service.setUsers(users);

        boolean bol = false;

        // 2. Act
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser(exampleId);

        for (int i = 0; i < usersWithoutCurrentUser.size(); i++) {
            if (!exampleId.equals(usersWithoutCurrentUser.get(i).getUserId())) {
                bol = true;
                break;
            }
            i++;
        }

        // 3. Assert
        assertTrue(bol);
    }

    @Test
    @DisplayName("return parameter of chooseUser is not empty")
    public void testChooseUserNotEmpty() {
        // 1. Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(123456L, "AntjeWinner", "StellaIstToll"));
        users.add(new User(456789L, "MartinTheBrain", "IchLiebeKBA"));

        // 2. Act
        Long chosenUserid = service.chooseUser();

        // 3. Assert
        assertNotNull(chosenUserid);
    }
    @Test
    @DisplayName("user chooses an opponent from given list of users")
    public void testChooseUser() {
        // 1. Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(123456L, "AntjeWinner", "StellaIstToll"));
        users.add(new User(456789L, "MartinTheBrain", "IchLiebeKBA"));

        boolean bol = false;

        // 2. Act
        Long chosenUserid = service.chooseUser();

        for (int i = 0; i < users.size(); i++) {
            if (chosenUserid.equals(users.get(i).getUserId())) {
                bol = true;
                break;
            }
            i++;
        }

        // 3. Assert
        assertTrue(bol);
    }
    @Test
    @DisplayName("user chooses a new password")
    public void changePassword() {
        // 1. Arrange
        User user = new User(123456L, "AntjeWinner", "StellaIstToll");
        String newPassword = "QWERTZ";

        // 2. Act
        service.changePassword(newPassword, user);

        // 3. Assert
        assertEquals(newPassword, user.getPassword());

    }
}
