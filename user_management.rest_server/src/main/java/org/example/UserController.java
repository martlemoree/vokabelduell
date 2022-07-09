package org.example;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
    public List<User> getUserListWOcurrentUser(@PathVariable("userName") String userName) { //throws UserNotFoundException
        List<User> userListWOcurrentUser = userService.getUserListWOcurrentUser(userName);
        return userListWOcurrentUser;
    }

    @GetMapping(value = "/all")
    public List<User> getUserList() {
        List<User> userList = userService.getUserList();
        return userList;
    }

    @GetMapping(value = "/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName) { //throws UserNotFoundException
        User user = userService.getUserByUserName(userName);
        return user;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createUser(@RequestBody User user) throws URISyntaxException {
        User newUser = userService.createUser(user.getUserName(), user.getPassword());
        URI uri = new URI("/user/" + newUser.getUserName());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "edit/{userName}")
    public ResponseEntity<?> changePassword(@PathVariable("userName") String userName, @RequestBody String password) {
        User user = userService.getUserByUserName(userName);
        userService.changePassword(password, user);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/delete/{userName}")
    public ResponseEntity<?> removeUser(@PathVariable("userName") String userName) {
        User user = userService.getUserByUserName(userName);
        boolean successful = userService.removeUser(user);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
