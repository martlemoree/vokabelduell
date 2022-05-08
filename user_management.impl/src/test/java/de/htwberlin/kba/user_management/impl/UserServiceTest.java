package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
        Long exampleId = service.getListOfUsers().get(0).getUserId();

        // 2. Act
        List<User> usersWithoutCurrentUser = service.getUserList(exampleId);

        // 3. Assert
        assertNotNull(usersWithoutCurrentUser);
    }

    @Test
    @DisplayName("get all users without current user")
    public void testGetUserList(){
        // 1. Arrange
        Long exampleId = service.getListOfUsers().get(0).getUserId();

        boolean bol = false;

        // 2. Act
        List<User> usersWithoutCurrentUser = service.getUserList(exampleId);

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
        Long exampleId = service.getListOfUsers().get(0).getUserId();
        List<User> usersWithoutCurrentUser = service.getUserList(exampleId);

        // 2. Act
        Long chosenUserid = service.chooseUser(usersWithoutCurrentUser);

        // 3. Assert
        assertNotNull(chosenUserid);
    }
    @Test
    @DisplayName("user chooses an opponent from list of users without current user")
    public void testChooseUser() {
        // 1. Arrange
        Long exampleId = service.getListOfUsers().get(0).getUserId();
        List<User> usersWithoutCurrentUser = service.getUserList(exampleId);

        boolean bol = false;

        // 2. Act
        Long chosenUserid = service.chooseUser(usersWithoutCurrentUser);

        for (int i = 0; i < usersWithoutCurrentUser.size(); i++) {
            if (chosenUserid.equals(usersWithoutCurrentUser.get(i).getUserId())) {
                bol = true;
                break;
            }
            i++;
        }

        // 3. Assert
        assertTrue(bol);
    }
}
