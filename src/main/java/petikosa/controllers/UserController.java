package petikosa.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import petikosa.dtos.UserDto;
import petikosa.services.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto getUserById(@PathParam("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public void createUser(@RequestBody UserDto user) {
        userService.createUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody UserDto user) {
        userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(@PathParam("id") long id) {
        userService.deleteUser(id);
    }
}
