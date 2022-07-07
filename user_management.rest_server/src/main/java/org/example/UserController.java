package org.example;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all/{userName}")
    public List<User> getUserListWOcurrentUser(@PathVariable("userName") String userName) {
        List<User> userListWOcurrentUser = userService.getUserListWOcurrentUser(userName);
        return userListWOcurrentUser;
    }

    @GetMapping(value = "/all")
    public List<User> getUserList() {
        List<User> userList = userService.getUserList();
        return userList;
    }

    @GetMapping(value = "/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName) {
        User user = userService.getUserByUserName(userName);
        return user;
    }

    @PostMapping(value = "/create")
    public String createUser(@RequestBody User user) {
        userService.createUser(user.getUserName(), user.getPassword());
        return "User was created successfully.";
    }

    @PutMapping(value = "edit/{userName}/{password}")
    public String changePassword(@PathVariable("userName") String userName,
                                 @PathVariable("password") String password) {
        User user = userService.getUserByUserName(userName);
        userService.changePassword(password, user);
        return "Password was changed successfully.";
    }

    @DeleteMapping(value = "/delete/{userName}")
    public String removeUser(@PathVariable("userName") String userName) {
        User user = userService.getUserByUserName(userName);
        userService.removeUser(user);
        return "User was deleted successfully.";
    }
}
