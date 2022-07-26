package org.example;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserAlreadyExistsException;
import de.htwberlin.kba.user_management.export.UserNotFoundException;
import de.htwberlin.kba.user_management.export.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
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
    public List<User> getUserListWOcurrentUser(@PathVariable("userName") String userName) throws UserNotFoundException {
        User user = userService.getUserByUserName(userName);
        List<User> userListWOcurrentUser = userService.getUserListWOcurrentUser(userName);
        return userListWOcurrentUser;
    }

    @GetMapping(value = "/all")
    public List<User> getUserList() {
        List<User> userList = userService.getUserList();
        return userList;
    }

    @GetMapping(value = "/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName) throws UserNotFoundException { //throws UserNotFoundException
        User user = userService.getUserByUserName(userName);
        return user;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createUser(@RequestBody User user) throws URISyntaxException, UserAlreadyExistsException {
        User newUser = userService.createUser(user.getUserName(), user.getPassword());
        URI uri = new URI("/user/" + newUser.getUserName());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "edit/{userName}")
    public ResponseEntity<?> changePassword(@PathVariable("userName") String userName, @RequestBody String password) throws UserNotFoundException {
        User user = userService.getUserByUserName(userName);
        userService.changePassword(password, user);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // TODO Martin: in UserDAO einfügen, dass bevor der user gelöscht wird, alle seine spiele gelöscht werden über named query
    @DeleteMapping(value = "/deleteId/{id}")
    public ResponseEntity<?> removeUserID(@PathVariable("name") String name) throws UserNotFoundException {
        boolean successful = userService.removeUserName(name);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
