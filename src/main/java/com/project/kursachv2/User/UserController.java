package com.project.kursachv2.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    // Основные маппинги
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userService.convertDTOToUser(userDTO));
    }

    @DeleteMapping("/{id}")
    public User deleteUserById(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@RequestBody UserDTO userDTO, @PathVariable long id) {
        return userService.updateUser(userService.convertDTOToUser(userDTO), id);
    }

}
