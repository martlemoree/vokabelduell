package org.example;

import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "http://localhost:8080/users")
    public ResponseEntity<List<User>> fetchUsers() {
        return ResponseEntity.ok(userService.getUserList());
    }

    /*
    @PostMapping(path = "http://localhost:8080/users/" + user.getId())
    public ResponseEntity<Void> createUser(@RequestBody )
        var user = userService.createUser()

     */
}
