package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class UserServiceTest {

    private UserService service;
    @Before
    public void setUp() {
        this.service = new UserServiceImpl();
    }

    @Test
    @DisplayName("getUserList gives back return parameter")
    public void testGetUserListNotEmpty(){
        // 1. Arrange
        List<User> users = new ArrayList<>();
        Long exampleId = 123456L;
        users.add(new User(exampleId, "AntjeWinner", "StellaIstToll"));
        users.add(new User(456789L, "MartinTheBrain", "IchLiebeKBA"));

        // 2. Act
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser(exampleId, users);

        // 3. Assert
        assertNotNull(usersWithoutCurrentUser);
    }

    @Test
    @DisplayName("get all users without current user")
    public void testGetUserList(){
        // 1. Arrange
        List<User> users = new ArrayList<>();
        Long exampleId = 123456L;
        users.add(new User(exampleId, "AntjeWinner", "StellaIstToll"));
        users.add(new User(456789L, "MartinTheBrain", "IchLiebeKBA"));

        boolean bol = false;

        // 2. Act
        List<User> usersWithoutCurrentUser = service.getUserListWOcurrentUser(exampleId, users);

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
        Long chosenUserid = service.chooseUser(users);

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
        Long chosenUserid = service.chooseUser(users);

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
}
