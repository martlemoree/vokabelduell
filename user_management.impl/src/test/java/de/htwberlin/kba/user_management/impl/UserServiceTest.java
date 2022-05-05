package de.htwberlin.kba.user_management.impl;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class UserServiceTest {

    private UserService service;
    @Before
    public void setUp() {
        this.service = new UserServiceImpl();
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

        // 3. Assert ist id in users enthalten
    }
}
